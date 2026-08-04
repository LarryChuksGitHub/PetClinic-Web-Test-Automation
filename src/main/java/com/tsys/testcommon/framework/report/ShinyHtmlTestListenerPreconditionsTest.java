package com.tsys.testcommon.framework.report;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Listeners({ShinyHtmlTestListener.class, ShinyHtmlReport.class, AnnotationTransformer.class, TestRetryListener.class})
public class ShinyHtmlTestListenerPreconditionsTest {

    ShinyHtmlTestListenerPreconditionsTest() {
        TestExecutionRegistry.setTestSide(TestSide.BACKEND);
        TestExecutionRegistry.setTestEnvUrl("http://testfail");
    }

    @BeforeMethod(alwaysRun = true)
    public void setupFailure() {
        log.warn("setupFailure");
        throw new AssertionError("Test passed. Dependent test must appear in the report");
    }

    @Test
    @Reference
    public void testReport() {
        log.warn("testReport called");
    }
}
