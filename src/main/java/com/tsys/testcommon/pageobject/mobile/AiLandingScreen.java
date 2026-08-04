package com.tsys.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tsys.testcommon.framework.utils.constant.NumericConstants;
import com.tsys.testcommon.framework.utils.retry.RetryUtils;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class AiLandingScreen extends MobileScreen {

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Lebenslagen']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Wohnen und Umzug']"),
            @AndroidBy(xpath = "//*[@text='Reichen Sie Ihren Umzugsantrag digital ein']"),
            @AndroidBy(xpath = "//android.widget.Button[@content-desc=\"Wohnen und Umzug. Reichen Sie Ihren Umzugsantrag digital ein\"]/android.widget.TextView[1]"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement houseRegisterText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Kindergeld für Kinder bei Eintritt der Volljährigkeit']"),
            @AndroidBy(xpath = "//*[@text='Geburt']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childSupportFeature;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.EditText[@content-desc='Geben Sie hier Ihre Anfrage ein.']"),
            @AndroidBy(accessibility = "Geben Sie hier Ihre Anfrage ein."),
            @AndroidBy(className = "android.widget.EditText"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement inputTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Mikrofon\"]"),
            @AndroidBy(xpath = "//android.widget.Button[@resource-id=\"chat-mic-button\"]"),


    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement micButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Anhang\"]"),
            @AndroidBy(xpath = "//android.widget.Button[@resource-id=\"chat-attach-button\"]"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement attachButton;

    @AndroidFindAll({
            @AndroidBy(accessibility = "Profil, "),
            @AndroidBy(xpath = "//android.view.View[@content-desc=\"Profil, \"]"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement profileButton;

    @AndroidFindAll({
            @AndroidBy(accessibility = "Profil, "),
            @AndroidBy(xpath = "//android.view.View[@content-desc=\"Service, \"]"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement serviceButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement nextButton;

    public AiLandingScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        RetryUtils.isConditionFulfilledWithWait( ()-> driver.getPageSource().contains("Hallo"), NumericConstants.NUMERIC_20);

        String pageSource = driver.getPageSource();
        pageSource = pageSource.toLowerCase();
        dippAssertions.assertThat(pageSource.contains("willkommen in der"))
                .as("Home page was not loaded properly, Willkommen was not displayed" )
                .isTrue();

         dippAssertions.assertThat(pageSource.contains("deutschland-app"))
                .as("Home page was not loaded properly, Deutschland-App was not displayed")
                .isTrue();

    }

    public void assertHomeScreenContent() {
        log.info("Asserting contents on the Homage screen");

        dippAssertions.assertThat(isElementDisplayedWithWait(screenText, NumericConstants.NUMERIC_20))
                .as("Lebenslagen info should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(houseRegisterText, NumericConstants.NUMERIC_3))
                .as("house register info should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(childSupportFeature, NumericConstants.NUMERIC_3))
                .as("AI Support info should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(inputTextField, NumericConstants.NUMERIC_3))
                .as("Input Text field should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(micButton, NumericConstants.NUMERIC_3))
                .as("Mic should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(attachButton, NumericConstants.NUMERIC_3))
                .as("Attach Button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(profileButton, NumericConstants.NUMERIC_3))
                .as("Profile button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(serviceButton, NumericConstants.NUMERIC_3))
                .as("Service button should be displayed")
                .isTrue();
    }

    public AiIntroScreen selectChildSupportAndNavigateToAiIntroScreen() {
        childSupportFeature.click();
        return new AiIntroScreen(driver);

    }

    public AiIntroScreen selectHouseRegistrationAndNavigateToAiIntroScreen() {
        houseRegisterText.click();
        return new AiIntroScreen(driver);

    }

}
