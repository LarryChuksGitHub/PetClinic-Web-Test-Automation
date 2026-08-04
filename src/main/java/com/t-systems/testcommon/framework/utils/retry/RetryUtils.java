package com.verimi.testcommon.framework.utils.retry;

import static com.verimi.testcommon.framework.utils.constant.NumericConstants.NUMERIC_1;
import static com.verimi.testcommon.framework.utils.constant.NumericConstants.WAIT_300;
import static com.verimi.testcommon.pageobject.web.Page.WAIT_SECONDS;
import static com.verimi.testcommon.pageobject.web.Page.waitForMilliSecs;
import static org.awaitility.Awaitility.with;
import static org.hamcrest.Matchers.greaterThan;

import java.sql.SQLException;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.awaitility.core.ConditionTimeoutException;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryUtils {

    public static final String RETRY_MESSAGE = "Condition not fulfilled. Tried {} times out of {}.";
    public static final String WAITING_FAILED_MESSAGE = "Waiting failed.";
    public static final int MAX_WAIT_TIME_IN_SECONDS = 20;
    public static final long MILLI_SECONDS_TO_SECONDS = 1000L;
    public static final String WAITING_FOR = "Waiting for ";


    private RetryUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void tryToWaitUntilConditionFulfilled(RetryStatement statement, int maxNumberOfRetries,
                                                        int pollingInMilliSecs, String logMessage) throws SQLException {
        log.info(logMessage);
        for (int i = 1; i <= maxNumberOfRetries; i++) {
            if (statement.evaluate()) {
                break;
            } else {
                try {
                    log.info(RETRY_MESSAGE, i, maxNumberOfRetries);
                    Thread.sleep(pollingInMilliSecs);
                } catch (InterruptedException e) {
                    log.error(WAITING_FAILED_MESSAGE, e);
                }
            }
        }
    }

    @SneakyThrows
    public static boolean isConditionFulfilledWithWait(RetryStatement condition, int waitTimeInSeconds) {
        final long timeoutMillis = (waitTimeInSeconds + 1) * MILLI_SECONDS_TO_SECONDS;
        final long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            if (condition.evaluate()) {
                return true;
            }

            log.info("Condition status: " + condition.evaluate());
            log.info(WAITING_FOR + ((System.currentTimeMillis() - startTime) / MILLI_SECONDS_TO_SECONDS) + " seconds...");
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        }
        return false;
    }

    @SneakyThrows
    public static boolean isConditionFulfilledWithLessWait(RetryStatement condition1, RetryStatement condition2, int waitTimeInSeconds) {
        final long timeoutMillis = (waitTimeInSeconds + 1) * MILLI_SECONDS_TO_SECONDS;
        final long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeoutMillis) {
            if (condition1.evaluate()) {
                return true;
            }
            if (condition2.evaluate()) {
                return false;
            }
            log.info("Condition status1: " + condition1.evaluate());
            log.info("Condition status2: " + condition1.evaluate());
            log.info(WAITING_FOR + ((System.currentTimeMillis() - startTime) / MILLI_SECONDS_TO_SECONDS) + " seconds...");
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        }
        return false;
    }

    @SneakyThrows
    public static boolean isConditionFulfilledAfter(RetryStatement condition, int waitTimeInSeconds) {
        getElapsedTimeWithCount(waitTimeInSeconds);
        return condition.evaluate();
    }

    public static int getElapsedTimeWithCount(int waitTimeInseconds) {
        int elapsedSeconds = 0;
        while (elapsedSeconds <= waitTimeInseconds) {

            log.info(WAITING_FOR + elapsedSeconds + WAIT_SECONDS);
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
            elapsedSeconds++;
        }
        return elapsedSeconds;
    }

    @SneakyThrows
    public static int waitForAnalyticsEvent(Callable<Integer> callable, String event, int initialEventCount) {
        int count = callable.call();
        if (count <= initialEventCount) {
            log.info("Waiting for analytics event: {}", event);
            try {
                return with()
                        .conditionEvaluationListener(condition -> log.info("{} (elapsed time {}, remaining time {})\n",
                                condition.getDescription(), condition.getElapsedTimeInMS(), condition.getRemainingTimeInMS()))
                        .await()
                        .pollInterval(1, TimeUnit.SECONDS)
                        .atMost(MAX_WAIT_TIME_IN_SECONDS, TimeUnit.SECONDS)
                        .until(callable, greaterThan(initialEventCount));
            } catch (ConditionTimeoutException e) {
                e.printStackTrace();
                log.info("Timed out waiting for analytics event: {}", event);
            }
        }
        log.info("Final count for analytics event {} is {}", event, count);
        return count;
    }

    public static void retry(int numRetries, Runnable method, String logMessage) {
        int retriesLeft = numRetries;
        while (retriesLeft > 0) {
            try {
                method.run();
                return;
            } catch (Exception e) {
                retriesLeft--;
                log.warn("Exception caught during retry attempt: {}", e.getMessage());
                waitForMilliSecs(WAIT_300);
            }
        }
        throw new IllegalStateException(logMessage);
    }

}