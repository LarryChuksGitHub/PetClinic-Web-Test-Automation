package com.tsys.testcommon.framework.report;

import static com.tsys.testcommon.config.hooks.Hooks.jobDuration;
import static com.tsys.testcommon.framework.utils.config.ConfigUtilities.DEV;
import static java.lang.String.valueOf;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import com.tsys.testcommon.framework.utils.config.ConfigUtilities;
import lombok.extern.slf4j.Slf4j;

/**
 * Listener to send test results on MSTeams
 * It is specified in build.gradle
 */
@Slf4j
@SuppressWarnings("unused")
public class MSTeamsReporter implements IReporter {

    private static final String BASE64_IMAGE_PREFIX = "data:image/png;base64,";
    private static final String GREEN_CHECK_IMAGE = "ms_teams_report_success.png";
    private static final String RED_CROSS_IMAGE = "ms_teams_report_fail.png";
    private static final String QUESTION_MARK_IMAGE = "ms_teams_report_skip.png";
    private long successfulCases;
    private long skippedCases;
    private long failedCases;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        try {
            countCases();
            String json = processTemplate();
            writeResultsJson(json);
        } catch (Exception e) {
            log.error("Error cretaing MS Teams report json", e);
        }
    }

    private void countCases() {
        successfulCases = TestExecutionRegistry.getTestCaseList().values().stream()
                .filter(testCase -> testCase.getResult().equals("Passed"))
                .count();

        skippedCases = TestExecutionRegistry.getTestCaseList().values().stream()
                .filter(testCase -> testCase.getResult().equals("Skipped"))
                .count();

        failedCases = TestExecutionRegistry.getTestCaseList().values().stream()
                .filter(testCase -> testCase.getResult().equals("Failed"))
                .count();
    }

    private String processTemplate() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("datetime", LocalDateTime.now(ZoneId.of("Europe/Berlin")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        params.put("environment", System.getenv(ConfigUtilities.ENV_VARIABLE_OF_TEST_ENV));
        Optional.ofNullable(jobDuration).ifPresentOrElse(
                jobDuration -> params.put("testDuration", jobDuration),
                () -> log.info("Job duration is null, probably build was not run on Gitlab")
        );

        params.put("activityImage", getActivityImageBase64(GREEN_CHECK_IMAGE));
        params.put("themeColor", "90EE90");

        if (System.getenv(ConfigUtilities.ENV_VARIABLE_OF_TEST_ENV).equals("prod")) {
            params.put("environment", DEV);
        }
        if (skippedCases > 0) {
            params.put("activityImage", getActivityImageBase64(QUESTION_MARK_IMAGE));
            params.put("themeColor", "FFD633");
        }
        if (failedCases > 0) {
            params.put("activityImage", getActivityImageBase64(RED_CROSS_IMAGE));
            params.put("themeColor", "FF3385");
        }
        params.put("passed", valueOf(successfulCases));
        params.put("skipped", valueOf(skippedCases));
        params.put("failed", valueOf(failedCases));

        String jsonTemplate = IOUtils.toString(
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("ms-teams-report-template.json")),
                StandardCharsets.UTF_8
        );
        for (Map.Entry<String, String> entry : params.entrySet()) {
            jsonTemplate = jsonTemplate.replace(String.format("${%s}", entry.getKey()), entry.getValue());
        }
        return jsonTemplate;
    }

    private String getActivityImageBase64(String fileName) throws IOException {
        byte[] file = IOUtils.resourceToByteArray(fileName,
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader()));
        return BASE64_IMAGE_PREFIX + Base64.encodeBase64String(file);
    }

    private void writeResultsJson(String json) throws IOException {
        File output = new File("build" + File.separator + "ms-teams-json-message.json");
        FileUtils.writeStringToFile(output, json, StandardCharsets.UTF_8);
    }
}
