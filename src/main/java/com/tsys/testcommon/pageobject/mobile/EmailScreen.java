package com.tsys.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class EmailScreen extends MobileScreen {


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Registrierung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='E-Mail-Adresse*']/following-sibling::android.widget.EditText"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement otpTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.Button")
    @iOSXCUITFindBy(accessibility = "Zurück")
    private WebElement backButton;


    public EmailScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(screenTitle);
    }

    public EmailScreen addEmail(String email) {
        waitUntilClickable(otpTextField).sendKeys(email);
        return new EmailScreen(driver);
    }

    public OtpScreen navigateToOtpScreen() {
        waitUntilClickable(nextButton).click();
        return new OtpScreen(driver);
    }
}
