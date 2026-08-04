package com.verimi.testcommon.pageobject.mobile;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;

public class TwoFaSetUpScreen extends MobileScreen {


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Deutschland-App PIN']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Weiter']"),
            @AndroidBy(xpath = "//android.widget.TextView[@text='Entsperren']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement nextButton;

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

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.view.View[@content-desc=\"PIN: leeres\"]/android.view.View[1]"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement pinField1;



    public TwoFaSetUpScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
    }

    public TwoFaSetUpScreen setUp2FaPin(String twoFaPin) {
        waitUntilClickable(pinField1).click();
        typePinAndConfirmPin(twoFaPin);
        return new TwoFaSetUpScreen(driver);
    }

    public TwoFaSetUpScreen typePinAndConfirmPin(String pinCode) {
        String pin = pinCode.substring(0, 6);
        String confirmPin = pinCode.substring(6, 12);
        new Actions(driver)
                .sendKeys(pin)
                .perform();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(2)); // Sending code very fast results occasional error
        new Actions(driver)
                .sendKeys(confirmPin)
                .perform();
        return this;
    }

    public TwoFaSetUpScreen typePIN(String pinCode) {
        new Actions(driver)
                .sendKeys(pinCode)
                .perform();
        return this;
    }


    public void addPin(String pin) {
        waitUntilClickable(pinField1).click();
        typePIN(pin);
        waitUntilClickable(nextButton).click();
    }

    public EmailScreen navigateToEmailScreen() {
        waitUntilClickable(nextButton).click();
        return new EmailScreen(driver);
    }

}
