package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import com.verimi.testcommon.framework.utils.retry.RetryUtils;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HouseRegisterChatScreen extends MobileScreen {

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Wohnsitzanmeldung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Ja, Daten übernehmen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement dataImportButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Hmm, ich schaue kurz nach. Vielen Dank. Wie ist Ihr Familienname?']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement lastNameQuestion;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Alle teilen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement selectAllButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Abbrechen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement cancelButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Kein Problem. Sie können alle Felder manuell ausfüllen.']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement dataNotImported;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='ai-assistant-form-tab']"),
            @AndroidBy(xpath = "//*[@content-desc='Wechsel zu Antragsmodus']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formularButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Anmeldung (Zuzug von außerhalb)']"),
            @AndroidBy(xpath = "//*[@resource-id='suggested-reply-Anmeldung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement registerComingFromAbroad;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Ummeldung (Umzug innerhalb Deutschlands)']"),
            @AndroidBy(xpath = "//*[@resource-id='suggested-reply-Ummeldung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement registerWithinGermany;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Hallo, ich bin Sandra, Ihre persönliche Assistentin. ']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement greeting;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='chat-input']"),
            @AndroidBy(id = "Geben Sie hier Ihre Anfrage ein."),
            @AndroidBy(xpath = "//*[@text='Geben Sie hier Ihre Anfrage ein.']"),
            @AndroidBy(xpath = "//*[@text='//android.widget.EditText[@content-desc=\"Geben Sie hier Ihre Anfrage ein.\"]']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement textInputField;

     @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='chat-mic-button']"),
             @AndroidBy(xpath = "//*[@content-desc='Mikrofon']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement micButton;

   @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='chat-attach-button']"),
            @AndroidBy(xpath = "//*[@content-desc='Anhang']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement attachButton;

 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='ai-assistant-voice-tab']"),
            @AndroidBy(xpath = "//*[@content-desc='Wechsel zu Voicechatmodus']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement avatar;

 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='ai-assistant-form-tab']"),
            @AndroidBy(xpath = "//*[@content-desc='Wechsel zu Antragsmodus']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formButton;


 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='ai-assistant-chat-tab']"),
            @AndroidBy(xpath = "//*[@content-desc='Wechsel zu Chatmodus']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement chatButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='chat-send-button']"),
            @AndroidBy(xpath = "//*[@content-desc='Senden']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement sendButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Wohnen Sie in Deutschland']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement answerText;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Nein']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement noButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Digitale Identität']"),
            @AndroidBy(xpath = "//*[@text='8 Felder angefragt']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement digitalIdentity;

    public HouseRegisterChatScreen(WebDriver driver) {
        super(driver);
    }


    @Override
    public void waitUntilPageLoads() {
    }

    public HouseRegisterChatScreen importData() {
        waitUntilVisible(screenTitle);
        waitUntilVisible(greeting);
        waitUntilClickable(dataImportButton).click();
        return this;
    }

    public HouseRegisterChatScreen cancelDataImport() {
        waitUntilClickable(cancelButton).click();
        return this;
    }

    public HouseRegisterChatScreen dataNotImported() {
        waitUntilClickable(dataNotImported).click();
        return this;
    }

    public HouseRegisterChatScreen dataImported() {
       boolean dataImported =  RetryUtils.isConditionFulfilledWithWait( ()-> driver.getPageSource().contains("Vornamen: HANS-GÜNTHER"), NumericConstants.NUMERIC_60);
        dippAssertions.assertThat(dataImported)
                .as("HANS-GÜNTHER eID Data was not imported")
                .isTrue();
        return this;
    }

    public void askForHouseRegistrationApplication(String text) {
        waitUntilClickable(textInputField).click();
        waitUntilClickable(textInputField).sendKeys(text);
        waitUntilClickable(sendButton).click();
    }


    public void waitForAnswerOptions() {
        log.info("Asserting next questions on the screen");

        RetryUtils.isConditionFulfilledWithWait( ()-> driver.getPageSource().contains("Vielen Dank"), NumericConstants.NUMERIC_6);
        String sourcePage = driver.getPageSource();
        sourcePage = sourcePage.toLowerCase();
        dippAssertions.assertThat(sourcePage.contains("ummeldung") || sourcePage.contains("anmeldung") || sourcePage.contains("staatsangehörigkeit"))
                .as("Answer should be displayed")
                .isTrue();
    }

    public HouseRegisterChatScreen confirmDataImport() {
        waitUntilVisible(digitalIdentity);
        waitUntilVisible(selectAllButton).click();
        return this;
    }

    public void assertContent() {
        dippAssertions.assertThat(isElementDisplayedWithWait(registerComingFromAbroad, NumericConstants.NUMERIC_10))
                .as("register Coming From Abroad button should be displayed")
                .isTrue();
        dippAssertions.assertThat(isElementDisplayedWithWait(registerWithinGermany, NumericConstants.NUMERIC_10))
                .as("register Within Germany button should be displayed")
                .isTrue();
        dippAssertions.assertThat(isElementDisplayedWithWait(micButton, NumericConstants.NUMERIC_10))
                .as("Mic button  should be displayed")
                .isTrue();
        dippAssertions.assertThat(isElementDisplayedWithWait(attachButton, NumericConstants.NUMERIC_10))
                .as("Attach Button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(avatar, NumericConstants.NUMERIC_10))
                .as("Avatar button  should be displayed")
                .isTrue();
     dippAssertions.assertThat(isElementDisplayedWithWait(formButton, NumericConstants.NUMERIC_10))
                .as("Form Button should be displayed")
                .isTrue();
     dippAssertions.assertThat(isElementDisplayedWithWait(chatButton, NumericConstants.NUMERIC_10))
                .as("Chat Button should be displayed")
                .isTrue();

    }

    public HouseRegistrationFormularScreen navigateToHouseRegistrationFormularScreen() {
        waitUntilClickable(formButton).click();
        return new HouseRegistrationFormularScreen(driver);
    }
}
