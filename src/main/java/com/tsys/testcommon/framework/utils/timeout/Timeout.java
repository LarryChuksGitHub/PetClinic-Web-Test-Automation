package com.tsys.testcommon.framework.utils.timeout;

public class Timeout {
    public static final int DEFAULT_PAGE_LOAD_TIME = 60;
    public static final int LONG_PAGE_LOAD_TIME = 100;
    public static final int NFA_PROCESS_TIME = 40;
    public static final int DEFAULT_ELEMENT_TIMEOUT = 20;
    public static final int ELEMENT_LOAD_TIMEOUT = 40;
    public static final int SHORT_TIMEOUT = 10;
    public static final int SHORTLY_TIME = 5;
    public static final int MICRO_TIMEOUT = 3;
    public static final int TEST_TIMEOUT_IN_MINUTES = 15;
    public static final int BROWSERSTACK_IDLE_TIMEOUT = 300;

    private Timeout() {
        throw new IllegalStateException("Utility class");
    }
}
