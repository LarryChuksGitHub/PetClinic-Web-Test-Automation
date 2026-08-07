package com.tsys.testcommon.framework.drivers.impl;

import static com.tsys.testcommon.config.Config.BROWSER;
import static com.tsys.testcommon.config.Config.isBrowserStackRun;
import static com.tsys.testcommon.config.Config.isMobileDeviceCloudRun;
import static com.tsys.testcommon.config.cloudprovider.CloudProvider.DEUTSCHLAND_APP_NAME;
import static com.tsys.testcommon.model.common.Browser.CHROME_ANDROID;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tsys.testcommon.config.Config;
import com.tsys.testcommon.config.cloudprovider.BrowserstackProvider;
import com.tsys.testcommon.config.cloudprovider.CloudProvider;
import com.tsys.testcommon.config.cloudprovider.MobileDeviceCloudProvider;
import com.tsys.testcommon.config.hooks.ScenarioManager;
import com.tsys.testcommon.framework.drivers.BaseMobileWebDriverProvider;
import com.tsys.testcommon.framework.drivers.RemoteWebDriverProvider;
import com.tsys.testcommon.framework.report.TestCase;
import com.tsys.testcommon.model.common.Locale;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.options.BaseOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AndroidWebDriverProvider extends BaseMobileWebDriverProvider implements RemoteWebDriverProvider {

    private static final String CURRENT_DEVICE_NAME = System.getenv("DEVICE_NAME");
    private static final String APP_NAME = System.getenv("APP_NAME");
    public static final String ANDROID_DEVICE = getRandomAndroidDevice();

    private static String getRandomAndroidDevice() {
        List<String> androidDevicesList = new ArrayList<>(Arrays.asList(
                "Google Pixel 6",
                "Huawei P30",
                "Samsung Galaxy S22"
        ));
        String selectedAndroidDevice;
        if (APP_NAME.contains(DEUTSCHLAND_APP_NAME) && CloudProvider.getInstance() instanceof MobileDeviceCloudProvider) {
            selectedAndroidDevice = "Google Pixel 9 (Nr 1)";
        } else if (APP_NAME.contains(DEUTSCHLAND_APP_NAME) && CloudProvider.getInstance() instanceof BrowserstackProvider) {
            selectedAndroidDevice = "Google Pixel 9";
        } else {
            selectedAndroidDevice = androidDevicesList.toArray()[new Random().nextInt(androidDevicesList.size())].toString();
        }

        log.info("[SELECTED MOBILE DEVICE].." + selectedAndroidDevice);
        TestCase.setMobileDevice(selectedAndroidDevice);

        return selectedAndroidDevice;
    }

    public AndroidWebDriverProvider(String methodName) {
        super(methodName);
    }

    public AndroidWebDriverProvider() {
        super(null);
    }

    @Override
    public RemoteWebDriver start() throws JSONException {
        try {
            return new AndroidDriver(new URL(LOCAL_APPIUM_HUB_URL), getLocalCapabilities());
        } catch (MalformedURLException e) {
            log.error("Incorrect hub URL format specified", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public RemoteWebDriver startRemote() throws Exception {
        DesiredCapabilities desiredCapabilities = getCapabilities();
        Map<String, Object> cloudProviderOptions = new HashMap<>();

        setProjectCapability(cloudProviderOptions, getProjectName() + "_" + Config.CURRENT_ENV, getBuildNumber(), methodName);
        startCloudTunnel(cloudProviderOptions);
        applyCustomCapabilities(desiredCapabilities);
        CloudProvider.getInstance().configureBrowserCapabilities(desiredCapabilities, cloudProviderOptions);

        log.info("Creating ANDROID Driver..");
        AndroidDriver androidDriver = new AndroidDriver(new URL(CloudProvider.getInstance().getHubUrl()), desiredCapabilities);
        androidDriver.setFileDetector(new LocalFileDetector());
        log.info("[DONE] Creating ANDROID Driver..");
        return androidDriver;
    }

    @Override
    protected DesiredCapabilities getCapabilities() throws JSONException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        String appName = APP_NAME.contains(getAppFileExtension()) ? APP_NAME : APP_NAME + getAppFileExtension();
        if(isMobileDeviceCloudRun()) {
            desiredCapabilities.setCapability("digitalai:accessKey", ((MobileDeviceCloudProvider) CloudProvider.getInstance()).getKey());
            desiredCapabilities.setCapability("digitalai:testName", ScenarioManager.getScenario().getName());
        }
        HashMap<String, Object> browserstackOptions = new HashMap<>();
        // Test target is mobile browser
        if (BROWSER == CHROME_ANDROID) {
            desiredCapabilities.setCapability("browserName", Browser.CHROME.browserName());
            // https://www.browserstack.com/app-automate/capabilities?tag=w3c and https://www.browserstack.com/automate/capabilities
            // Unfortunately any BrowserStack capability passed outside bstack:options will not be honoured.
            browserstackOptions.put("realMobile", "true");
            browserstackOptions.put("local", "false");

            // Test target is mobile app
        } else {
            if(isBrowserStackRun()){
                desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "browserstack.enableBiometric", "true");
                desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "browserstack.enableCameraImageInjection", "true");
                desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "browserstack.uiautomator2ServerReadTimeout", "5400000");
                browserstackOptions.put("local", "false"); //suggested by BS support team to avoid no internet issue on android
                browserstackOptions.put("deviceOrientation", "portrait");
            }
            desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + UiAutomator2Options.APP_OPTION, CloudProvider.getInstance().getAppUrl(appName));

        }
        CloudProvider.getInstance().configureBrowserCapabilities(desiredCapabilities, browserstackOptions);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + UiAutomator2Options.DEVICE_NAME_OPTION, ANDROID_DEVICE);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "language", Locale.getLocale());
        return setCommonCapabilities(desiredCapabilities);
    }

    private DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // Test target is mobile browser
        if (BROWSER == CHROME_ANDROID) {
            desiredCapabilities.setCapability(UiAutomator2Options.BROWSER_NAME_OPTION, Browser.CHROME.browserName());

            // Test target is mobile app
        } else {
            desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + UiAutomator2Options.APP_OPTION, getMobileAppPath(APP_NAME));
        }
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + UiAutomator2Options.DEVICE_NAME_OPTION, CURRENT_DEVICE_NAME); // If you have connected more than one device to your local machine then please specify the device name here for the device you want to run the tests on.
        return setCommonCapabilities(desiredCapabilities);
    }

    private DesiredCapabilities setCommonCapabilities(DesiredCapabilities desiredCapabilities) {
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + BaseOptions.AUTOMATION_NAME_OPTION, "UiAutomator2");
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "unicodeKeyboard", true);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "resetKeyboard", true);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "uiautomator2ServerReadTimeout", 5400000);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "interactiveDebugging", true);
        return desiredCapabilities;
    }

    @Override
    protected String getPlatform() {
        return "Android";
    }

    @Override
    protected String getAppFileExtension() {
        if (System.getenv("APP_NAME").contains(".aab")){
            return ".aab";
        }
        return ".apk";
    }

    @Override
    protected String getBrowser() {
        return String.valueOf(Browser.CHROME);
    }

}
