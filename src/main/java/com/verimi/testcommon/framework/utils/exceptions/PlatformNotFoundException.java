package com.verimi.testcommon.framework.utils.exceptions;

public class PlatformNotFoundException extends Exception {
    public PlatformNotFoundException (String platformName) {
        super("Platform could not be detected: " + platformName);
    }
}
