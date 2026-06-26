package com.verimi.testcommon.framework.report;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unused")
public class RetryAnalyzer implements IRetryAnalyzer {

    private int counter = 0;
    private final int retryLimit = 4;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryLimit) {
            counter++;
            log.info("TestCase failed retry "
                    .concat(String.valueOf(counter))
                    .concat(" out from ")
                    .concat(String.valueOf(retryLimit)));
            return true;
        }
        return false;
    }
}