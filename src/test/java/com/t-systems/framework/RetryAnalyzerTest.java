package com.verimi.framework;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.verimi.testcommon.config.hooks.Hooks;
import com.verimi.testcommon.framework.report.Reference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryAnalyzerTest extends Hooks {

    private int beforeInvocations = 0;
    private int testInvocations = 0;

    @BeforeTest
    public void beforeConfig() {
        log.info("Calling @BeforeTest #{}", beforeInvocations++);
        if (beforeInvocations == 1) {
            throw new IllegalStateException("@BeforeTest");
        }
    }

    /**
     * Given: Before test config fails
     * Then: Before test is restarted and passes on the second attempt
     * When: Test is called and fails
     * Then: Test is retried by retry analyzer
     * And:  Before test is not restarted anymore
     * And: Expected total invocations count is 2 for @BeforeTest and 3 for @Test
     * And: Test report has failure
     */
    @Reference
    @Test(alwaysRun = true)
    public void testCall() {
        log.info("Calling @Test #{}", testInvocations++);
        throw new IllegalStateException("@Test");
    }

    @AfterTest(alwaysRun = true)
    public void checkAfterTest() {
        log.info("@BeforeTest calls: {}, @Test calls: {}", beforeInvocations, testInvocations);
        Assert.assertEquals(beforeInvocations, 2, "BeforeTest invocations is incorrect");
        Assert.assertEquals(testInvocations, 3, "Test invocations is incorrect");
    }
}
