package com.verimi.testcommon.framework.utils.testhelper;

public class TestContext {

    private static final ThreadLocal<Throwable> LAST_EXCEPTION =
            new ThreadLocal<>();

    public static Throwable getLastException() {

        return LAST_EXCEPTION.get();
    }

    public static void setLastException(Throwable throwable) {

        LAST_EXCEPTION.set(throwable);
    }

    public static void clear() {

        LAST_EXCEPTION.remove();
    }
}
