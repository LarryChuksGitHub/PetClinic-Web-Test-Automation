package com.tsys.testcommon.pageobject.mobile;

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
            @iOSXCUITBy(xpath = "//*[contains(@label,'Willkommen in der Deutschland-App')]"),
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.view.View[@text='Das Bürgeramt.\n" +
                    "Einfach. Digital. Überall.']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Das Bürgeramt.&#10;Einfach. Digital. Überall.')]"),
    })
    private WebElement screenText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Los geht’s']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//XCUIElementTypeButton[@name=\"welcome-start\"]"),
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

    public AdultChildSupportTutorialScreen navigateToAdultChildSupportOnboardingScreen() {
        waitUntilClickable(losGehtButton).click();
        return new AdultChildSupportTutorialScreen(driver);
    }
}
