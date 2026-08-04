package com.verimi.testcommon.framework.report;

public enum TestSide {
    BACKEND("Backend"), FRONTEND("Frontend");

    private final String side;

    TestSide(String side) {
        this.side = side;
    }

    public String getText() {
        return side;
    }
}
