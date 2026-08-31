package com.petclinic.testcommon.framework.utils.logging;

import org.slf4j.MDC;

public class TestLogHelper {
    private static final String TEST_NAME = "testname";

    /**
     * Adds the test name to MDC so that sift appender can use it and log the new
     * log events to a different file
     * @param name name of the new log file
     */

    public static void setTestName(String name) {
        MDC.put(TEST_NAME, name);
    }

    /**
     * Removes the key (log file name) from MDC
     */
    public static void removeTestName() {
        String name = MDC.get(TEST_NAME);
        MDC.remove(TEST_NAME);
    }
}
