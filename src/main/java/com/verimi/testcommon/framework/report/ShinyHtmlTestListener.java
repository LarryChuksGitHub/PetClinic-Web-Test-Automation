package com.verimi.testcommon.framework.report;

import static java.util.Arrays.asList;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import com.verimi.testcommon.framework.utils.logging.TestScenarioLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShinyHtmlTestListener implements ITestListener {

    private static final String SCREENSHOT_LOCATION = "screenshots";
    private static final String PACKAGENAME = "com.verimi.frontendtest.";

    @Override
    public void onTestStart(ITestResult result) {
        try {
            ITestNGMethod testNGmethod = result.getMethod();
            ConstructorOrMethod testNGinternalMethod = testNGmethod.getConstructorOrMethod();
            Method javaMethod = testNGinternalMethod.getMethod();
            beforeTestMethod(result.getTestClass().getRealClass(), javaMethod);
        } catch (Exception e) {
            log.error("ShinyHtmlTestListener/start error", e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            postMethodActions(result.getMethod().getConstructorOrMethod().getMethod(), result);
        } catch (Exception e) {
            log.error("ShinyHtmlTestListener/success error", e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            if (TestExecutionRegistry.getTestSide() == TestSide.FRONTEND) {
                long paramsHashCode = Stream.of(result.getParameters())
                        .mapToLong(o -> o != null ? o.hashCode() : 0)
                        .sum();
                result.setAttribute(TestExecutionRegistry.SCREENSHOT_ATTRIBUTE, SCREENSHOT_LOCATION + "/" + result.getInstanceName().
                        replace(PACKAGENAME, "") + "-" + result.getName() + paramsHashCode + ".jpg");
            }
            postMethodActions(result.getMethod().getConstructorOrMethod().getMethod(), result);
        } catch (Exception e) {
            log.error("ShinyHtmlTestListener/failure error", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            postMethodActions(result.getMethod().getConstructorOrMethod().getMethod(), result);
        } catch (Exception e) {
            log.error("ShinyHtmlTestListener/skipped error", e);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        if (TestExecutionRegistry.getTestSide() == TestSide.BACKEND) {
            TestExecutionRegistry.readEndpointsListForCoverageReport();
        }
    }

    @Override
    public void onFinish(ITestContext context) {

    }

    private void beforeTestMethod(Class<?> clazz, Method method) {
        log.info("Start method {}", method.getName());
        Reference reference = method.getAnnotation(Reference.class);
        if (reference != null) {
            log.info("Start test for JIRA {}", asList(reference.story()));
            log.info("Start test for acceptance criteria {}", asList(reference.ac()));
        }
        log.info("Scenario: \n" + TestScenarioLogger.getTestJavadoc(clazz, method) + "\n");
    }

    private void postMethodActions(Method method, ITestResult testResult) {
        Reference reference = method.getAnnotation(Reference.class);
        try {
            TestExecutionRegistry.addTestCase(reference, testResult);
            log.info("Test case {} added to registry", method.getName());
            if (TestExecutionRegistry.getTestSide() == TestSide.BACKEND) {
                TestExecutionRegistry.processApiReference(reference, testResult);
            }
        } catch (Exception e) {
            log.error("Something went wrong when adding test execution to test registry");
            log.error(e.getLocalizedMessage());
        }
        if (testResult.getStatus() != ITestResult.SKIP && reference != null) {
            log.info("Finish test for JIRA {}", asList(reference.story()));
        }
    }
}
