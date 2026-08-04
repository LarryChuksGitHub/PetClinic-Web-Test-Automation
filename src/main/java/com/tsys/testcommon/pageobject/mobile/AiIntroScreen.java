package com.tsys.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tsys.testcommon.framework.asserts.DippAssertions;
import com.tsys.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class AiIntroScreen extends MobileScreen {

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.view.View[@text='Wie es funktioniert']"),
            @AndroidBy(xpath = "//*[@text='Antrag stellen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Beispiel Menü zur Auswahl und Wechsel zwischen den drei Interaktionsmodi\"]"),
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Beispiel Menü zur Auswahl und Wechsel zwischen den drei Interaktionsmodi\"]/android.view.ViewGroup[1]"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement interactionModeButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Chat']"),
            @AndroidBy(xpath = "//*[@text='Unterhalten Sie sich mit der KI im Chat in natürlicher Sprache']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement chatText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Alle Informationen im Blick, übersichtlich im Formular']"),
            @AndroidBy(xpath = "//*[@text='Formular']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formularText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='KI-Avatar']"),
            @AndroidBy(xpath = "//*[@text='Ein KI-Avatar führt Sie per Sprachinteraktion durch Ihren Antrag']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement avatarText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.EditText[@content-desc='Geben Sie hier Ihre Anfrage ein.']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement inputTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement nextButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formularButton;

    public AiIntroScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(screenTitle);
    }

    public AiIntroScreen assertScreenContent() {
        log.info("Asserting contents on the screen");

        DippAssertions dippAssertions = new DippAssertions();
        dippAssertions.assertThat(isElementDisplayedWithWait(interactionModeButton, NumericConstants.NUMERIC_20))
                .as("Interaction mode should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(chatText, NumericConstants.NUMERIC_3))
                .as("Chat text should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(formularText, NumericConstants.NUMERIC_3))
                .as("Formular text should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(avatarText, NumericConstants.NUMERIC_3))
                .as("Avatar text should be displayed")
                .isTrue();
        return this;
    }

    public AdultChildSupportChatScreen navigateToAdultChildSupportChatScreen() {
        nextButton.click();
        return new AdultChildSupportChatScreen(driver);
    }

public HouseRegisterChatScreen navigateToHouseRegistrationChatScreen() {
        nextButton.click();
        return new HouseRegisterChatScreen(driver);
    }


}
