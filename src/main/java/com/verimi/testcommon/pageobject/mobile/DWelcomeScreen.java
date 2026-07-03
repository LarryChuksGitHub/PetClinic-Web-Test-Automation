package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DWelcomeScreen extends MobileScreen {


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.view.View[@text='Willkommen in\n" +
                    "der Deutschland-App']")

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.view.View[@text='Das Bürgeramt.\n" +
                    "Einfach. Digital. Überall.']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Los geht’s']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement losGehtButton;


    @AndroidFindBy(xpath = "//android.widget.Button")
    @iOSXCUITFindBy(accessibility = "Zurück")
    private WebElement backButton;


    public DWelcomeScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(screenTitle);
    }

    public AdultChildSupportTutorialScreen clickLosGehtButton() {
        waitUntilClickable(losGehtButton).click();
        return new AdultChildSupportTutorialScreen(driver);
    }

    public AdultChildSupportTutorialScreen navigateToAdultChildSupportOnboardingScreen() {
        clickLosGehtButton();
        return new AdultChildSupportTutorialScreen(driver);
    }
}
