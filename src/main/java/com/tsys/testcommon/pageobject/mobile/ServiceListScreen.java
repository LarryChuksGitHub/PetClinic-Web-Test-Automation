package com.tsys.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;

public class ServiceListScreen extends MobileScreen {


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Verfügbare Services']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Zur Registrierung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement registerButton;

    public ServiceListScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(screenTitle);
    }

    public RegistrationScreen starRegistration() {
        waitUntilClickable(registerButton).click();
        return new RegistrationScreen(driver);
    }

}
