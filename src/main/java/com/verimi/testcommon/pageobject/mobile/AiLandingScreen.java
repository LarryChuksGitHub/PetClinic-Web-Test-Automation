package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.verimi.testcommon.framework.asserts.DippAssertions;
import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class AiLandingScreen extends MobileScreen {

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Hallo,\n" +
                    "willkommen in der\n" +
                    "Deutschland-App']"),
            @AndroidBy(xpath = "//*[@text='Hallo,']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement greetingText;

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
            @AndroidBy(xpath = "//*[@text='Umziehen']"),

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
        isElementDisplayedWithWait(greetingText, NumericConstants.NUMERIC_20);
    }

    public void assertHomeScreenContent() {

        log.info("Asserting contents on the Homage screen");
        DippAssertions dippAssertions = new DippAssertions();
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
    }

    public AiIntroScreen navigateToAiIntroScreen() {
        childSupportFeature.click();
        return new AiIntroScreen(driver);

    }
}
