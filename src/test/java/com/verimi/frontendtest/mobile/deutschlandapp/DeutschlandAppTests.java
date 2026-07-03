package com.verimi.frontendtest.mobile.deutschlandapp;

import static com.verimi.testcommon.config.Config.isAndroid;
import static com.verimi.testcommon.config.Config.isIOS;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.verimi.testcommon.config.hooks.Hooks;
import com.verimi.testcommon.flows.DeutschlandAppFlow;
import com.verimi.testcommon.framework.report.Reference;
import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import com.verimi.testcommon.framework.utils.random.RandomUtilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;
import io.appium.java_client.ios.IOSDriver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeutschlandAppTests extends Hooks {
    private static final String EMAIL = RandomUtilities.generateRandomEmail();


    /**
     * Scenario: Erfolgreiche Nutzer Registrierung mit Mock eID
     * <p>
     * Given die Bürger App ist gestartet
     * And der Nutzer befindet sich im Onboarding
     * When der Nutzer die Registrierung startet
     * And die Mock eID aktiviert
     * And eine gültige E-Mail-Adresse eingibt
     * And das OTP erfolgreich bestätigt
     * Then wird die Registrierung erfolgreich abgeschlossen
     * And die eID Daten werden unter Nachweise angezeigt
     * And KI homgepage wird angezeigt
     */

    @SneakyThrows
    @Test()
    @Reference()
    public void deutschlandAppRegistrationFlow() {
        cleanAndSetUpTestData();
        DeutschlandAppFlow deutschlandAppFlow = new DeutschlandAppFlow(getDriver());
        deutschlandAppFlow.registerUserWithPhoneNumber(EMAIL);
    }


    public void terminateAndOpenDeutschlandApp() {
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) getDriver());
            String packageName = androidDriver.getCurrentPackage();
            if (packageName == null || !packageName.contains(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID)) {
                openDeutschlandApp();
            }
            for (int i = 0; i < 3; i++) {
                try {
                    clearAppData();

                    log.info("Initiating Deutschland App termination with package name: {}", androidDriver.getCurrentPackage());
                    androidDriver.terminateApp(
                            DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID,
                            new AndroidTerminateApplicationOptions()
                                    .withTimeout(Duration.ofSeconds(10)));
                    log.info("Deutschland app terminated successfully");
                    break;
                } catch (WebDriverException e) {
                    if (i == 2) throw e;
                    // Static wait to wait for app to be terminated
                    Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NumericConstants.NUMERIC_2));
                }
            }
            openDeutschlandApp();

        } else if (isIOS()) {
            clearAppData();
            IOSDriver iosDriver = ((IOSDriver) getDriver());
            iosDriver.terminateApp(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID);
            openDeutschlandApp();
        }
    }

    public void clearAppData() {
        log.info("Clear app data");
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) getDriver());
            String packageName = androidDriver.getCurrentPackage();
            if (!packageName.contains(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID)) {
                packageName = DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID;
            }
            androidDriver.executeScript("clearApp", Map.of("appId", packageName));
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        } else if (isIOS()) {
            // iOS does not provide a direct way to clear app data, but reinstalling the app helps.
            IOSDriver iosDriver = ((IOSDriver) getDriver());
            log.info("iOS does not support clearing app data programmatically. Ensure that the app is deleted");
            String bundleId =
                    iosDriver.getCapabilities()
                            .getCapability("bundleId")
                            .toString();

            iosDriver.terminateApp(bundleId);
            iosDriver.removeApp(bundleId);
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        }

    }


    public void openDeutschlandApp() {

        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) getDriver());
            try {
                androidDriver.activateApp(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID);
            } catch (WebDriverException e) {
                androidDriver.activateApp(DEUTSCHLAND_APP_BUNDLE_ID_ANDROID);
            }
            androidDriver.rotate(ScreenOrientation.PORTRAIT);
        } else if (isIOS()) {
            IOSDriver iosDriver = ((IOSDriver) getDriver());
            iosDriver.terminateApp(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID);

        }
        log.info("Opened Deutschland App successfully");
    }

    @SneakyThrows
    private void cleanAndSetUpTestData() {
        log.info("Clean up test data if exists and set up new test data");
    }


    @AfterMethod(alwaysRun = true)
    private void deleteUser() {
        log.info("Ensure user data is deleted");
    }
}


