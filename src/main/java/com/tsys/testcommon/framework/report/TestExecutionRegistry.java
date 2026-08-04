package com.tsys.testcommon.framework.report;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestResult;

import com.tsys.testcommon.framework.utils.cloud.CloudUrlReference;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestExecutionRegistry {

    static final String SCREENSHOT_ATTRIBUTE = "ScreenShotAttribute";
    public static final String CLOUD_REPORT_URL_ATTRIBUTE = "cloud_report_url";
    @Getter
    private static final ConcurrentMap<String, TestCase> testCaseList = new ConcurrentHashMap<>();
    @Getter
    private static final ConcurrentMap<String, Integer> summary = new ConcurrentHashMap<>();
    @Getter
    private static final ConcurrentMap<String, TestResultCollector> typeSummary = new ConcurrentHashMap<>();
    @Getter
    private static final ConcurrentMap<String, TestResultCollector> domainSummary = new ConcurrentHashMap<>();
    @Getter
    private static final ConcurrentMap<String, TestResultCollector> methodSummary = new ConcurrentHashMap<>();
    @Getter
    private static final ConcurrentMap<String, Map<String, Integer>> serviceSummary = new ConcurrentHashMap<>();
    @Getter
    private static final ConcurrentMap<String, String> serviceCoverages = new ConcurrentHashMap<>();
    @Getter
    private static int total;
    @Getter
    private static final TestResultCollector summaryResult = new TestResultCollector();
    @Getter
    private static final Registrar errorStats = new Registrar();
    @Getter
    @Setter
    private static String testEnvUrl;
    @Getter
    @Setter
    private static TestSide testSide;
    @Getter
    private static final Registrar endpointStats = new Registrar();

    static void readEndpointsListForCoverageReport() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (Stream<String> stream = Files
                .lines(Paths.get(
                        Objects.requireNonNull(classloader.getResource("endpoints.txt")).getFile()
                                .replaceFirst("^/(.:/)", "$1")))) {
            stream.forEach(line -> endpointStats.define(line));
        } catch (IOException e) {
            log.error("Cannot read endpoints.txt", e);
        } catch (Exception e) {
            log.error("Unexpected error happened ", e);
        }
    }

    private static String getPercent(float number) {
        float percent = number / (float) testCaseList.size() * 100;
        return String.format("%.2f", percent);
    }

    static String getTotalPercent() {
        return getPercent(testCaseList.size() - getNumByResult(ITestResult.SKIP));
    }

    static long getNumByResult(int testResultId) {
        String resultName;
        switch (testResultId) {
            case ITestResult.SUCCESS:
                resultName = "Passed";
                break;
            case ITestResult.FAILURE:
                resultName = "Failed";
                break;
            case ITestResult.SKIP:
                resultName = "Skipped";
                break;
            default:
                resultName = "UnknownStatus";
        }
        return testCaseList.values().stream()
                .filter(testCase -> testCase.getResult().startsWith(resultName)).count();
    }

    static String getSkippedPercent() {
        return getPercent(getNumByResult(ITestResult.SKIP));
    }

    static String getFailedPercent() {
        return getPercent(getNumByResult(ITestResult.FAILURE));
    }

    static String getPassedPercent() {
        return getPercent(getNumByResult(ITestResult.SUCCESS));
    }

    static void addTestCase(Reference reference, @NotNull ITestResult result) {
        TestCase testCase;
        final String cloudUrl = result.getAttribute(CLOUD_REPORT_URL_ATTRIBUTE) != null ?
                ((CloudUrlReference) result.getAttribute(CLOUD_REPORT_URL_ATTRIBUTE)).getCloudReportUrl() :
                null;
        try {
            testCase = TestCase.builder()
                    .title(result.getName() + " " + TestCase.mobileDevice + addOptionalParameters(result))
                    .stories(reference.story())
                    .acs(reference.ac())
                    .description(result.getMethod().getDescription())
                    .log(getFormattedExceptionForLog(result.getThrowable(), result.getStatus(), result.getName()))
                    .bugs(reference.bug())
                    .result(getStatusStringAndRegister(result.getStatus(), reference.bug()))
                    .throwable(result.getThrowable())
                    .screenShotPath((String) result.getAttribute(SCREENSHOT_ATTRIBUTE))
                    .browserstackReportUrl(cloudUrl)
                    .execTime(Duration.of(result.getEndMillis() - result.getStartMillis(), ChronoUnit.MILLIS))
                    .build();
        } catch (Exception e) {
            testCase = TestCase.builder()
                    .title(result.getName()  + " " + TestCase.mobileDevice + addOptionalParameters(result))
                    .stories(new String[]{"-"})
                    .acs(new String[]{"-"})
                    .description(result.getMethod().getDescription())
                    .log("")
                    .bugs(new String[]{"-"})
                    .result(getStatusStringAndRegister(result.getStatus(), reference.bug()))
                    .throwable(result.getThrowable())
                    .screenShotPath((String) result.getAttribute(SCREENSHOT_ATTRIBUTE))
                    .browserstackReportUrl(cloudUrl)
                    .execTime(Duration.of(result.getEndMillis() - result.getStartMillis(), ChronoUnit.MILLIS))
                    .build();
        }
        addToErrorStats(result.getThrowable(), result.getName() + addOptionalParameters(result));
        testCaseList.put(result.getName() + addOptionalParameters(result), testCase);
        ++total;
    }

    private static void addToErrorStats(Throwable exceptionFromTestcase, String testName) {
        if (exceptionFromTestcase == null) {
            return;
        }
        String exceptionClassname = exceptionFromTestcase.getClass().getName();
        errorStats.add(exceptionClassname, testName);
    }

    private static String getLog(int status, String name) {
        if (status == ITestResult.SKIP) {
            return "";
        }
        StringBuilder builder = new StringBuilder("<h4>Standard Output</h4>");
        try {
            getLogEntriesForTC(builder, name);
        } catch (IOException e) {
            log.error("Cannot read " + "build" + File.separator + "logfiles" + File.separator + name + ".log", e);
        }
        return builder.toString();
    }

    private static void getLogEntriesForTC(StringBuilder builder, String name) throws IOException {
        boolean isTcEntry = false;
        List<String> allLines = Files.readAllLines(Paths.get("build" + File.separator + "logfiles" + File.separator + name + ".log"));
        for (String line : allLines) {
            if (!isTcEntry && !(line.matches(".*Start test for .*"))) {
                builder.append(line).append("<br>");
            }
            if (line.matches(".*Start test for .*")) {
                builder.append("<h5>Test steps</h5>");
                isTcEntry = true;
            }
            if (isTcEntry) {
                builder.append(line).append("<br>");
            }
        }
    }

    private static String getFormattedExceptionForLog(Throwable thrown, int status, String name) {
        StringBuilder builder = new StringBuilder();

        if (thrown != null) {
            builder.append("<h4>Error Message and Stacktrace</h4><span class=\"code\"><pre>");
            builder.append(StringEscapeUtils.escapeHtml(thrown.getMessage()));
            builder.append("<br><br>");
            Arrays.asList(thrown.getStackTrace()).stream().forEach(stack -> {
                builder.append("  " + stack);
                builder.append("<br>");
            });
            builder.append("</pre></span><br>");
        }

        builder.append(getLog(status, name));

        return builder.toString();
    }

    static void processApiReference(Reference reference, ITestResult result) {
        String[] apis = reference.api();
        for (String api : apis) {
            endpointStats.add(api, "!!! ", result.getName() + addOptionalParameters(result));
        }
    }

    private static String addOptionalParameters(ITestResult result) {
        if (result.getParameters().length > 0) {
            return "(" + StringUtils.join(result.getParameters(), ", ")
                    .replace("<", "&lt;") + ")";
        }
        return "";
    }

    private static String getStatusStringAndRegister(int testResultStatus, String[] bugs) {
        List<String> bugsList = Collections.emptyList();
        if (bugs != null)
            bugsList = Arrays.asList(bugs);
        summaryResult.addResult(testResultStatus, bugsList);
        switch (testResultStatus) {
            case ITestResult.SUCCESS:
                return "Passed";
            case ITestResult.FAILURE:
                return "Failed";
            case ITestResult.SKIP:
                return "Skipped";
        }
        return "Invalid TestNG status value";
    }
}
