package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;

public class TelephoneNumberScreen extends MobileScreen {


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Handy Nummer']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.view.View[@content-desc=\"Ländervorwahl auswählen\"]"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement phoneNumberTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Bestätigen']"),
            @AndroidBy(xpath = "//*[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement confirm;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Ablehnen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement reject;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Mail erneut senden']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement sendAgainButton;

    public TelephoneNumberScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
    }

    public TelephoneNumberScreen reject() {
        waitUntilClickable(reject).click();
        return new TelephoneNumberScreen(driver);
    }

    public TelephoneNumberScreen confirm() {
        waitUntilClickable(confirm).click();
        return this;
    }

    public TelephoneNumberScreen addPhoneNumber(String phoneNumber) {
        waitUntilVisible(phoneNumberTextField);
        typePhoneNumber(phoneNumber);
        return this;
    }

    public TelephoneNumberScreen typePhoneNumber(String phoneNumber) {
        new Actions(driver)
                .sendKeys(phoneNumber)
                .perform();
        return this;
    }


    public IdentityScreen navigateToIdentityScreen(String phoneNumber) {
        addPhoneNumber(phoneNumber);
        confirm();
        return new IdentityScreen(driver);
    }

}
