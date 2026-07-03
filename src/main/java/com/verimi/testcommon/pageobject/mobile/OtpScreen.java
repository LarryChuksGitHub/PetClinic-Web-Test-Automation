package com.verimi.testcommon.pageobject.mobile;


import static com.verimi.testcommon.config.Config.isAndroid;
import static com.verimi.testcommon.config.Config.isIOS;
import static com.verimi.testcommon.config.hooks.Hooks.DEUTSCHLAND_APP_BUNDLE_ID_ANDROID;
import static com.verimi.testcommon.config.hooks.Hooks.DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID;

import java.time.Duration;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.google.common.util.concurrent.Uninterruptibles;
import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OtpScreen extends MobileScreen {

    public static final String TSY_MAIL_HOG_URL = "https://mailhog:KivpPassword1!@mailhog.dev.kivp.eudi-telekom.de/";
    public static final String TSY_MAIL_SUBJECT = "Ihr Bestaetigungscode fuer die KIVP-App";

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Eingabe One-Time Passwort']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='One-Time Passwort*']/following-sibling::android.widget.EditText"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement otpField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement nextButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Nachweise']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement proofButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Mail erneut senden']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement sendAgainButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='App Pin']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement appPinText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Entsperren']"),
            @AndroidBy(xpath = "//*[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement unblock;


    public OtpScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(screenTitle);
    }

    public OtpScreen enterOpt(String email, String twoFaPin) {
        String otp = getOpt(email);
        openDeutschlandApp();
        waitUntilClickable(proofButton).click();
        setMode(ScreenOrientation.PORTRAIT);
        int counter = 0;
        while (isElementDisplayedWithWait(appPinText, NumericConstants.NUMERIC_5) && counter < 3) {
            new TwoFaSetUpScreen(driver).typePIN(twoFaPin);
            waitUntilClickable(unblock).click();
            counter++;
        }
        waitUntilClickable(otpField).sendKeys(otp);
        return this;
    }

    private void setMode(ScreenOrientation mode) {
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            androidDriver.rotate(mode);
        }
    }


    public void openDeutschlandApp() {

        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            try {
                androidDriver.activateApp(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID);
            } catch (WebDriverException e) {
                androidDriver.activateApp(DEUTSCHLAND_APP_BUNDLE_ID_ANDROID);
            }
            androidDriver.rotate(ScreenOrientation.PORTRAIT);
        } else if (isIOS()) {
            IOSDriver iosDriver = ((IOSDriver) driver);
            iosDriver.terminateApp(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID);

        }
        log.info("Opened Deutschland App successfully");
    }

    @SneakyThrows
    public String getOpt(String email) {
        String otp = new MailHogScreen(driver).getOtpCode(TSY_MAIL_HOG_URL, email);
        int counter = 0;
        while (otp == null && counter < 3) {
            log.warn("OTP code not found in email. Retrying...");
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NumericConstants.NUMERIC_3)); // Wait for the email to arrive
            counter++;
            otp = new MailHogScreen(driver).getOtpCode(TSY_MAIL_HOG_URL, email);
        }
        log.info("OTP code retrieved from email: {}", otp);
        return otp;
    }


    public TelephoneNumberScreen navigateToTelePhoneScreen() {
        waitUntilClickable(nextButton).click();
        return new TelephoneNumberScreen(driver);
    }

    public IdentityScreen navigateToIdentityScreen() {
        waitUntilClickable(nextButton).click();
        return new IdentityScreen(driver);
    }

}
