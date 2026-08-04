package com.verimi.testcommon.pageobject.mobile;

import static com.verimi.testcommon.config.Config.LOAD_WAIT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.verimi.testcommon.framework.asserts.DippAssertions;
import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import com.verimi.testcommon.framework.utils.retry.RetryUtils;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdultChildSupportChatScreen extends MobileScreen {

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Kindergeld ab 18']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

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
            @AndroidBy(xpath = "//*[@resource-id='ai-assistant-voice-tab']"),
            @AndroidBy(xpath = "//*[@content-desc='Wechsel zu Voicechatmodus']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement avatarButton;

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
            @AndroidBy(xpath = "//*[@text='Ja']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement yesButton;

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
            @AndroidBy(xpath = "//*[@text='Hallo, ich bin Sandra, Ihre persönliche Assistentin. ']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement greeting;


    @AndroidFindAll({
            @AndroidBy(id = "chat-input"),
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

    public AdultChildSupportChatScreen(WebDriver driver) {
        super(driver);
    }


    @Override
    public void waitUntilPageLoads() {
    }

    public AdultChildSupportChatScreen importData() {
        waitUntilVisible(screenTitle);
        waitUntilVisible(greeting);
        waitUntilClickable(dataImportButton).click();
        return this;
    }

    public AdultChildSupportChatScreen cancelDataImport() {
        waitUntilClickable(cancelButton).click();
        return this;
    }

    public AdultChildSupportChatScreen dataNotImported() {
        waitUntilClickable(dataNotImported).click();
        return this;
    }

    public AdultChildSupportChatScreen dataImported() {
        boolean dataImported =  RetryUtils.isConditionFulfilledWithWait( ()-> driver.getPageSource().contains("Vornamen: HANS-GÜNTHER"), LOAD_WAIT);
        dippAssertions.assertThat(dataImported)
                .as("HANS-GÜNTHER eID Data was not imported")
                .isTrue();
        return this;
    }

    public void askForAdultChildSupportApplication(String text) {
        waitUntilClickable(textInputField).click();
        waitUntilClickable(textInputField).sendKeys(text);
        waitUntilClickable(sendButton).click();
    }

    public void waitForAnswer() {
        waitUntilVisible(yesButton);
        waitUntilVisible(noButton);
    }

    public void waitForAnswerOptions() {
        log.info("Asserting contents on the screen");
        DippAssertions dippAssertions = new DippAssertions();
        dippAssertions.assertThat(isElementDisplayedWithWait(yesButton, NumericConstants.NUMERIC_10))
                .as("Yes button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(noButton, NumericConstants.NUMERIC_10))
                .as("No button should be displayed")
                .isTrue();
    }

    public AdultChildSupportChatScreen confirmDataImport() {
        waitUntilVisible(digitalIdentity);
        waitUntilVisible(selectAllButton).click();
        return this;

    }

    public AdultChildSupportFormularScreen navigateToAdultChildSupportFormularScreen() {
        waitUntilClickable(formularButton).click();
        return new AdultChildSupportFormularScreen(driver);
    }
    public AdultChildSupportFormularScreen navigateToAdultChildSupportAvatarScreen() {
        waitUntilClickable(avatarButton).click();
        return new AdultChildSupportFormularScreen(driver);
    }

    public AvatarScreen switchToAvatarScreen() {
        waitUntilClickable(avatarButton).click();
        return new AvatarScreen(driver);
    }
}
