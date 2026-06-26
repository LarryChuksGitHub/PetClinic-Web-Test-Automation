package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;

public class DataProtectionTermsAndConditionScreen extends MobileScreen {

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Datenschutz und Nutzungsbedingungen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Bitte stimmen Sie den Nutzungsbedingungen zu und nehmen die Datenschutzhinweise zur Kenntnis.']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenText;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Datenschutzerklärung akzeptieren']/following-sibling::android.widget.CheckBox"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement acceptDataProtectionTerms;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Nutzungsbedingungen akzeptieren']/following-sibling::android.widget.CheckBox"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement acceptUsageTerms;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Mobilfunknummer übermitteln']/following-sibling::android.widget.CheckBox"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement transferPhoneNumber;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement nextButton;

    public DataProtectionTermsAndConditionScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(screenTitle);
        waitUntilVisible(screenText);
    }

    public DataProtectionTermsAndConditionScreen acceptTermsAndCondition() {
        waitUntilVisible(acceptDataProtectionTerms).click();
        waitUntilVisible(acceptUsageTerms).click();
        waitUntilVisible(transferPhoneNumber).click();
        return this;
    }

    public PostCodeScreen navigateToPostcodeScreen() {
        waitUntilClickable(nextButton).click();
        return new PostCodeScreen(driver);
    }

}
