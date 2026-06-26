package com.verimi.testcommon.framework.drivers;

import java.io.File;

public abstract class BaseMobileWebDriverProvider extends BaseRemoteWebDriverProvider implements LocalWebDriverProvider {

    protected static final String LOCAL_APPIUM_HUB_URL = "http://127.0.0.1:4723/wd/hub";
    protected static final String APPIUM_CAPABILITY_PREFIX = "appium:";

    public BaseMobileWebDriverProvider(String methodName) {
        super(methodName);
    }

    protected String getMobileAppPath(String appName) {
        return new File("mobileapps/" + getPlatform() + "/" + appName + getAppFileExtension()).getAbsolutePath();
    }

    protected abstract String getPlatform();
    protected abstract String getAppFileExtension();
}
