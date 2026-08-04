package com.tsys.testcommon.framework.report;

import static com.tsys.testcommon.framework.utils.config.ConfigUtilities.ENV_VARIABLE_OF_TEST_ENV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShinyHtmlReport implements IReporter {

    private static final Version currentVersion = Configuration.VERSION_2_3_23;
    private String ragDescription;
    private List<TestCase> successfulCases = new ArrayList<>();
    private List<TestCase> failedCases = new ArrayList<>();
    private List<TestCase> buglessFailedCases = new ArrayList<>();
    private List<TestCase> skippedCases = new ArrayList<>();

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {
        log.info("Start creating shiny html report");
        try {
            createReport();
        } catch (Exception e) {
            log.error("Error creating the shiny html report", e);
        } finally {
            log.info("Finish creating shiny html report: {}",
                new File("test-report.html").getAbsolutePath());
        }
    }

    private void createReport() {
        splitTestCasesByResultAndBugs();

        Map<String, Object> root = new HashMap<>();
        root.put("test_side", TestExecutionRegistry.getTestSide().getText());
        root.put("test_environment", System.getenv(ENV_VARIABLE_OF_TEST_ENV));
        root.put("test_url", TestExecutionRegistry.getTestEnvUrl());
        root.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        root.put("datetime",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        root.put("total", TestExecutionRegistry.getTestCaseList().size());
        root.put("total_percent", TestExecutionRegistry.getTotalPercent());
        root.put("passed", TestExecutionRegistry.getNumByResult(ITestResult.SUCCESS));
        root.put("passed_percent", TestExecutionRegistry.getPassedPercent());
        root.put("skipped", TestExecutionRegistry.getNumByResult(ITestResult.SKIP));
        root.put("skipped_percent", TestExecutionRegistry.getSkippedPercent());
        root.put("failed", TestExecutionRegistry.getNumByResult(ITestResult.FAILURE));
        root.put("failed_percent", TestExecutionRegistry.getFailedPercent());
        root.put("bugless_failed", buglessFailedCases.size());
        root.put("buglessFailedCases", buglessFailedCases);
        root.put("failedCases", failedCases);
        root.put("skippedCases", skippedCases);
        root.put("successfulCases", successfulCases);
        root.put("errorStats", TestExecutionRegistry.getErrorStats().getRegister());
        root.put("groupsSummary", TestExecutionRegistry.getSummary());
        root.put("domainSummary", TestExecutionRegistry.getDomainSummary());
        root.put("typeSummary", TestExecutionRegistry.getTypeSummary());
        root.put("ragDescription", ragDescription);
        root.put("ragStatusColor", getRagStatusColor());
        writeHtml(root);
    }

    private String getRagStatusColor() {
        ragDescription = "";
        return "#00ff00";
    }

    private void writeHtml(Map<String, Object> root) {
        try {
            Files.createDirectories(Paths.get("build" + File.separator + "test-report"));
            Template temp = getConfiguration(this.getClass())
                    .getTemplate("test-report.html.template");
            String path = "." + File.separator + "build" + File.separator + "test-report" + File.separator + "test-report.html";
            File file = new File(path);
            Writer out = new FileWriter(file);
            temp.process(root, out);
        } catch (IOException | TemplateException e) {
            log.error("Cannot write test-report.html", e);
        }
    }

    private void splitTestCasesByResultAndBugs() {
        successfulCases = TestExecutionRegistry.getTestCaseList().values().stream()
                .filter(testCase -> testCase.getResult().equals("Passed"))
                .collect(Collectors.toList());

        skippedCases = TestExecutionRegistry.getTestCaseList().values().stream()
                .filter(testCase -> testCase.getResult().equals("Skipped"))
                .collect(Collectors.toList());

        failedCases = TestExecutionRegistry.getTestCaseList().values().stream()
                .filter(testCase -> testCase.getResult().equals("Failed") && testCase.getBugs().length != 0)
                .collect(Collectors.toList());

        buglessFailedCases = TestExecutionRegistry.getTestCaseList().values().stream()
                .filter(testCase -> testCase.getResult().equals("Failed") && testCase.getBugs().length == 0)
                .collect(Collectors.toList());
    }

    static Configuration getConfiguration(Class clazz) {
        Configuration cfg = new Configuration(currentVersion);
        try {
            cfg.setDirectoryForTemplateLoading(new File("."));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setAPIBuiltinEnabled(true);
        cfg.setClassForTemplateLoading(clazz, "/");
        return cfg;
    }
}