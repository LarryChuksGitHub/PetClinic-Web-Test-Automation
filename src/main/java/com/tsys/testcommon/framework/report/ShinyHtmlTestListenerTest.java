package com.tsys.testcommon.framework.report;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import lombok.extern.slf4j.Slf4j;

@Listeners({ShinyHtmlTestListener.class, ShinyHtmlReport.class})
@Slf4j
public class ShinyHtmlTestListenerTest {

    ShinyHtmlTestListenerTest() {
        TestExecutionRegistry.setTestSide(TestSide.BACKEND);
        TestExecutionRegistry.setTestEnvUrl("http://sec1");
    }

    @Test
    @Reference(story = "DEV-123",

            ac = "AC-7",
            api = "GET dipp/api/visko-rulez",
            bug = {"DEV-111", "DEV-100"}
            )
    public void testSuccessful() {
        log.info("Successful story");
        log.info("Line success 1");
        log.info("Line success 2");
        log.info("Line success 3");
        Assert.assertTrue(true, "Successful test");
    }

    @Test
    @Reference(story = "DEV-333",

            ac = "AC-1, AC-2",
            api = "GET dipp/api/visko-sad",
            bug = {"DEV-100"}
            )
    public void testFail() {
        log.info("Failure info");
        log.info("Line failure 1");
        log.info("Line failure 2");
        log.info("Line failure 3");
        Assert.fail("Intentionally failing test");
    }

    @Test(dependsOnMethods = {"testFail"}) //dependency on failed test makes this skipped
    @Reference(story = "DEV-1891",

            ac = "AC-5, AC-1.1",
            api = "PUT dipp/api/empty",
            bug = {"DEV-5000"}
            )
    public void testSkip() {
        log.info("Skipped, should not get into report...");
        log.info("Line skipped 1");
        log.info("Line skipped 2");
        log.info("Line skipped 3");
        Assert.fail("Skipped test");
    }
}
