package com.tsys.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tsys.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DataProtectionTutorialScreen extends MobileScreen {


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Ihre Daten']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Ihre Daten')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Datenschutz und Nutzungsbedingungen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Datenschutz und Nutzungsbedingungen')]"),
    })
    private WebElement termsAndConditionScreen;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Nachweise']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Nachweise')]"),
    })
    private WebElement proofButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Sicher & Datenschutzkonform']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Sicher & Datenschutzkonform')]"),
    })
    private WebElement screenText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Weiter')]"),
    })
    private WebElement nextButton;


    @AndroidFindBy(xpath = "//android.widget.Button")
    @iOSXCUITFindBy(accessibility = "Zurück")
    private WebElement backButton;


    public DataProtectionTutorialScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
    }

    public DataProtectionTermsAndConditionScreen navigateToTermsAndCondition() {
        waitUntilClickable(nextButton).click();
        waitUntilClickable(nextButton).click();
        if (!isElementDisplayedWithWait(termsAndConditionScreen, NumericConstants.NUMERIC_5)) {
            waitUntilClickable(proofButton).click();
        }
        return new DataProtectionTermsAndConditionScreen(driver);
    }
}
