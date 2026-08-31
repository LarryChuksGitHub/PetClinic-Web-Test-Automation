package com.petclinic.testcommon.framework.utils.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IConfigurable;
import org.testng.IConfigureCallBack;
import org.testng.ITestResult;

public interface RetryBeforeTestConfig extends IConfigurable {

    Logger RETRY_LOGGER = LoggerFactory.getLogger(RetryBeforeTestConfig.class);
    int BEFORE_TEST_RETRIES_COUNT = 4;

    default void run(IConfigureCallBack callBack, ITestResult testResult) {
        callBack.runConfigurationMethod(testResult);
        if (testResult.getThrowable() != null) {
            RETRY_LOGGER.warn("Caught exception in method {}: {}", testResult.getMethod(), testResult.getThrowable().getMessage(),
                    testResult.getThrowable());
            for (int i = 0; i < BEFORE_TEST_RETRIES_COUNT; i++) {
                RETRY_LOGGER.warn("Retrying attempt #{} method {}", i, testResult.getMethod());
                callBack.runConfigurationMethod(testResult);
                if (testResult.getThrowable() == null) {
                    break;
                }
            }
        }
    }
}
