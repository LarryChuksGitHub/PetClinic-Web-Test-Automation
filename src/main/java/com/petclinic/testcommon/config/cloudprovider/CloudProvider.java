package com.petclinic.testcommon.config.cloudprovider;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;


public abstract class CloudProvider {


    protected static final String APPIUM_CAPABILITY_PREFIX = "appium:";
    private static CloudProvider instance;

    public static CloudProvider getInstance() {
        if (instance == null) {
            if (Boolean.parseBoolean(System.getProperty("browserStackRun", "false"))) {
                instance = new BrowserstackProvider();
            }
            else if (Boolean.parseBoolean(System.getProperty("lambdaTestRun", "false"))) {
            }
        }
        return instance;
    }

    abstract String getUserName();

    abstract String getKey();

    public abstract String getHubUrl();

    public static boolean isCloudRun() {
        return Boolean.parseBoolean(System.getProperty("browserStackRun", "false"))
                || Boolean.parseBoolean(System.getProperty("lambdaTestRun", "false"))
                || Boolean.parseBoolean(System.getProperty("mobileDeviceCloudRun", "false"));
    }

    public abstract String getAppId(String app) throws JSONException;

    public abstract String getAppUrl(String app) throws JSONException;
    public abstract String getCloudProviderName() throws JSONException;

    public abstract String getNetworkLogs(String sessionId);
    @NotNull
    public abstract String getDeviceLogs(String sessionId) throws IOException;

    public abstract String logReportUrl(String sessionId);

    public abstract void setTestResult(ITestResult testResult, String sessionId) throws Exception;

    public abstract void configureProjectAttributes(final Map<String, Object> cloudProviderOptions,
                                                    final String projectName,
                                                    final String buildNumber,
                                                    final String sessionName);


    public abstract String uploadMediaFile(DocumentImageName imageName);

    public abstract String uploadProcessIdQr(final File file);

    public abstract void configureBrowserCapabilities(final DesiredCapabilities desiredCapabilities,
                                                      final Map<String, Object> cloudProviderOptions);

    public abstract DesiredCapabilities getIosCapabilities(final String iosDeviceName,
                                                           final String iosDeviceVersion,
                                                           final String appFileExtension);
}
