package com.verimi.testcommon.framework.drivers.impl;

import static com.verimi.testcommon.config.Config.BROWSER;
import static com.verimi.testcommon.config.Config.PLATFORM_NAME;
import static com.verimi.testcommon.model.common.Browser.SAFARI_IOS;
import static com.verimi.testcommon.model.common.Platform.IPAD;
import static org.openqa.selenium.remote.Browser.SAFARI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.internal.collections.Pair;

import com.verimi.testcommon.config.Config;
import com.verimi.testcommon.config.cloudprovider.CloudProvider;
import com.verimi.testcommon.framework.drivers.BaseMobileWebDriverProvider;
import com.verimi.testcommon.framework.drivers.RemoteWebDriverProvider;
import com.verimi.testcommon.framework.report.TestCase;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IosWebDriverProvider extends BaseMobileWebDriverProvider implements RemoteWebDriverProvider {

    private static final String DEVICE_NAME = System.getenv("DEVICE_NAME");
    private static final String DEVICE_UDID = System.getenv("DEVICE_UDID");
    private static final String DEVICE_OS_VERSION = System.getenv("DEVICE_OS_VERSION");
    public static final String APP_NAME = System.getenv("APP_NAME");

    public static Pair<String, String> getRandomIosDevice() {
        log.info("[SELECT MOBILE DEVICE]..");
        Map<String, String> phonesAvailable = new LinkedHashMap<>();
        if (PLATFORM_NAME == IPAD) {
            phonesAvailable.put("iPad Air 5", "26");
        } else {
            phonesAvailable.put("iPhone 15", "26.0");
        }
        int index = new Random().nextInt(phonesAvailable.size());
        try {
            return new Pair<>(phonesAvailable.keySet().toArray()[index].toString(),
                    phonesAvailable.values().toArray()[index].toString());
        } finally {
            log.info("[DONE]Selecting IOS Device and OS Version.." + new Pair<>(phonesAvailable.keySet().toArray()[index].toString(),
                    phonesAvailable.values().toArray()[index].toString()));
        }
    }

    public final Pair<String, String> selectedDevice = getRandomIosDevice();
    public final String iosDeviceName = selectedDevice.first();
    public final String iosDeviceVersion = selectedDevice.second();

    public IosWebDriverProvider(String methodName) {
        super(methodName);
    }

    public IosWebDriverProvider() {
        super(null);
    }

    @Override
    public RemoteWebDriver start() {
        try {
            return new IOSDriver(new URL(LOCAL_APPIUM_HUB_URL), getLocalCapabilities());
        } catch (MalformedURLException e) {
            log.error("Incorrect hub URL format specified", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public RemoteWebDriver startRemote() throws Exception {
        TestCase.setMobileDevice(" " + iosDeviceName + " iOS " + iosDeviceVersion);
        DesiredCapabilities desiredCapabilities = getCapabilities();
        Map<String, Object> browserstackOptions = new HashMap<>();

        setProjectCapability(browserstackOptions, getProjectName() + "_" + Config.CURRENT_ENV, getBuildNumber(), methodName);
        startCloudTunnel(browserstackOptions);
        applyCustomCapabilities(desiredCapabilities);
        CloudProvider.getInstance().configureBrowserCapabilities(desiredCapabilities, browserstackOptions);


        log.info("Creating IOS Driver..");
        IOSDriver iosDriver = new IOSDriver(new URL(CloudProvider.getInstance().getHubUrl()), desiredCapabilities);
        iosDriver.setFileDetector(new LocalFileDetector());
        log.info("[DONE] Creating IOS Driver..");
        return iosDriver;
    }

    @Override
    protected DesiredCapabilities getCapabilities() throws JSONException {
        return CloudProvider.getInstance().getIosCapabilities(iosDeviceName, iosDeviceVersion, getAppFileExtension());
    }

    private DesiredCapabilities getLocalCapabilities() {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.MAC);
        // Test target is mobile browser
        if (BROWSER == SAFARI_IOS) {
            desiredCapabilities.setBrowserName(SAFARI.browserName());
            // Test target is mobile app
        } else {
            desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.APP_OPTION, getMobileAppPath(APP_NAME));
        }
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.PLATFORM_VERSION_OPTION, DEVICE_OS_VERSION); // Specify the OS version of your connected iOS device
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.NO_RESET_OPTION, true);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.AUTOMATION_NAME_OPTION, "XCUITest");
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "useNewWDA", false);
        // Team id for Apple account
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "xcodeOrgId", "M5ZHRV8H44");
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "xcodeSigningId", "iPhone Developer");
        // Device ID to run test on real device
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.UDID_OPTION, DEVICE_UDID); // Change the UDID to the device UDID of your connected device
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.DEVICE_NAME_OPTION, DEVICE_NAME); // Change the device name to the device name of your connected device
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.AUTO_ACCEPT_ALERTS_OPTION, Boolean.TRUE);
        return desiredCapabilities;
    }

    @Override
    protected String getPlatform() {
        return "iOS";
    }

    @Override
    protected String getAppFileExtension() {
        return ".ipa";
    }

    @Override
    protected String getBrowser() {
        return String.valueOf(SAFARI);
    }

}
