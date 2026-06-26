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
            @AndroidBy(xpath = "//*[@text='Akademischer Titel: DR. EH. DR.\n" +
                    "Stadt: HAMBURG\n" +
                    "Postleitzahl: 22043\n" +
                    "Straße: WEG NR. 12 8E\n" +
                    "Geburtsdatum: 25.01.1946\n" +
                    "Familienname: VON DREBENBUSCH-DALGOẞEN\n" +
                    "Geschlecht: unknown\n" +
                    "Vornamen: HANS-GÜNTHER']"),
            @AndroidBy(xpath = "//*[@text='Vornamen: HANS-GÜNTHER']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement dataImported;


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
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Senden\"]"),
            @AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"Senden\"]"),
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
        waitUntilVisible(dataImported);
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

    public void confirmDataImport() {
        waitUntilVisible(digitalIdentity);
        waitUntilVisible(selectAllButton).click();

    }
}
