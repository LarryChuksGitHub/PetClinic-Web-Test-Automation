package com.petclinic.testcommon.framework.utils.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unused")
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRY_COUNT = 2;
    private int count = MAX_RETRY_COUNT;

    @Override
    public boolean retry(ITestResult result) {
        boolean retryAgain = false;
        if (count > 0) {
            System.out.println("Going to retry test case: " + result.getMethod() + ", " + ((
                    (MAX_RETRY_COUNT - count) + 1)) + " out of " + MAX_RETRY_COUNT);
            retryAgain = true;
            --count;
        }
        return retryAgain;
    }
}
