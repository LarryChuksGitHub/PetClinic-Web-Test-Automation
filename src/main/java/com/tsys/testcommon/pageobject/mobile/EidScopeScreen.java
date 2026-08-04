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

public class EidScopeScreen extends MobileScreen {

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Ausweisdaten']"),
            @AndroidBy(xpath = "//*[@text='Ihre Daten']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Gelesene Daten']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement sharedData;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Geburtsort']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement birthPlace;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='• Geburtsort\n" +
                    "• Geburtsdatum\n" +
                    "• Familienname\n" +
                    "• Vorname(n)\n" +
                    "• “Gültig bis“\n" +
                    "• Pseudonym\n" +
                    "• Wohnortbestätigung\n" +
                    "• Altersbestätigung']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement eidScopeList;

 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Bundesministerium für Digitales und Staatsmodernisierung']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement ministryText;

 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Familienname']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement familyName;


 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Vorname']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement firstName;

 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Gültigkeit']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement validity;

 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Pseudonym']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement pseudonym;


 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Wohnortbestätigung']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement placeConfirmation;

 @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Alterbestätigung']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement ageConfirmation;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Weiter']"),
            @AndroidBy(xpath = "//*[@text='Erlauben']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement nextButton;

    public EidScopeScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(screenTitle);
    }

    public EidScopeScreen assertEidScopeToBeDelivered() {
        log.info("Asserting contents on the screen");

        DippAssertions dippAssertions = new DippAssertions();
        dippAssertions.assertThat(isElementDisplayedWithWait(sharedData, NumericConstants.NUMERIC_30))
                .as("Title of the eID scope should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(eidScopeList, NumericConstants.NUMERIC_3))
                .as("All eid scope list should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(ministryText, NumericConstants.NUMERIC_3))
                .as("Ministry Text should be displayed")
                .isTrue();
        return this;
    }

    public EidPinScreen navigateToEidPinScreen() {
        nextButton.click();
        return new EidPinScreen(driver);

    }
}
