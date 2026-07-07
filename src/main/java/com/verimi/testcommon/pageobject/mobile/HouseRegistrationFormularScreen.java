package com.verimi.testcommon.pageobject.mobile;

import static com.verimi.testcommon.model.common.accessibility.androidaccessibility.AndroidAccessibilityMobleElement.TEXT;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HouseRegistrationFormularScreen extends MobileScreen {

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
            @AndroidBy(xpath = "//*[@resource-id='select-anmeldetyp.registrierungstyp']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement registrationTypeTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Ummeldung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement ummeldung;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Anmeldung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement anmeldung;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='VON DREBENBUSCH-DALGOẞEN']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-personendaten.familienname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement lastNameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-personendaten.geburtsname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement birthNameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='HANS-GÜNTHER']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-personendaten.vorname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement firstNameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='DR. EH. DR.']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-personendaten.doktorgrad']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement drTitleTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='25.01.1946']"),
            @AndroidBy(xpath = "//*[@resource-id='date-picker-personendaten.geburtsdatum-input']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement birthDateTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='HAMBURG']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-personendaten.geburtsort']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement birthPlaceTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-personendaten.geburtsland']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement birthLandTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-personendaten.geschlecht']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement genderTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-personendaten.staatsangehoerigkeit']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement citizenshipTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-personendaten.familienstand']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement familyStatusTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-personendaten.religionszugehoerigkeit']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement religionTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-ausweisdokumente.ausweis_typ']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement idTypeTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-ausweisdokumente.ausweis_typ-option-Personalausweis']"),
            @AndroidBy(xpath = "//*[@text='Personalausweis']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement idCard;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-ausweisdokumente.ausweis_typ-option-Reisepass']"),
            @AndroidBy(xpath = "//*[@text='Reisepass']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement passport;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-ausweisdokumente.ausweis_typ-option-beides']"),
            @AndroidBy(xpath = "//*[@text='beides']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement both;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-ausweisdokumente.personalausweis_seriennummer']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement idCardNumberTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-ausweisdokumente.personalausweis_behoerde']"),
            @AndroidBy(xpath = "//*[@text='Governikus']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement idCardIssuedOfficeTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='date-picker-ausweisdokumente.personalausweis_ausstellungsdatum-input']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement idCardIssuedDateTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='date-picker-ausweisdokumente.personalausweis_gueltig_bis-input']"),
            @AndroidBy(xpath = "//*[@text='30.06.2035']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement idCardValidityDateTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-bisherige_anschrift.bisherige_strasse']"),
            @AndroidBy(xpath = "//*[@text='WEG NR. 12']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formerStreetTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-bisherige_anschrift.bisherige_hausnummer']"),
            @AndroidBy(xpath = "//*[@text='8E']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formerHouseNumberTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-bisherige_anschrift.bisherige_plz']"),
            @AndroidBy(xpath = "//*[@text='22043']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formerPostCodeTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-bisherige_anschrift.bisherige_ort']"),
            @AndroidBy(xpath = "//*[@text='HAMBURG']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formerCityTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='date-picker-bisherige_anschrift.bisheriges_einzugsdatum-input']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement formerParkInDateTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-neue_anschrift.neue_strasse']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement newStreetTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-neue_anschrift.neue_hausnummer']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement newHouseNumberTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-neue_anschrift.neue_plz']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement newPostcodeTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-neue_anschrift.neue_ort']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement newCityTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-neue_anschrift.neue_ortsteil']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement cityPartTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-neue_anschrift.stockwerk_wohnungsnummer']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement flatNumberTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-neue_anschrift.zusatzangaben']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement additionalInfoTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-neue_anschrift.wohnhaft_bei']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement livingWithTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='date-picker-neue_anschrift.einzugsdatum-input']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement newParkInDateTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-neue_anschrift.auszugsdatum_gleich']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement parkOutDateAndParkInDateSameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-wohnverhaeltnis.eigentuemer']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement houseOwnerTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Ja (Eigentümer)']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement yesTheHouseOwner;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Nein (Mieter / sonstiger Nutzer)']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement notHouseOwner;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-wohnverhaeltnis.eigentuemer_selbsterklaerung']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement confirmHouseOwnership;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Bestätigt']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement confirmBestaetigt;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-familienverbund.kinder_mitwohnen']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement movingInWithKidsTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='number-field-familienverbund.anzahl_kinder']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement numberOfKidsTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-familienverbund.kinder_namen']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement namesOfTheKidsTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-familienverbund.kinder_geburtsdaten']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement birthDateOfTheKidsTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-einwilligungen.einwilligung_ausweisdaten']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement acceptIdCardUpdateTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Erteilt']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement confirmedErteilt;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Nicht erteilt']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement notConfirmed;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-einwilligungen.einwilligung_email']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement acceptEmailTransactionTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-einwilligungen.widerspruch_religionsgesellschaft']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement declineReligionInfoTransferTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Widerspruch']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement declineInfoTransfer;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Kein Widerspruch']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement dontDeclineInfoTransfer;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-einwilligungen.widerspruch_jubilaeumsmitteilungen']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement declineAgeAndMarriageJubileeTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-einwilligungen.widerspruch_wahlvorschlaege']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement declinePartyAndElectionTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='OK']"),
            @AndroidBy(xpath = "//*[@resource-id='android:id/button1']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement okButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='männlich']"),
            @AndroidBy(xpath = "//*[@text='Männlich']"),
            @AndroidBy(xpath = "//*[@content-desc='männlich']"),
            @AndroidBy(xpath = "//*[@content-desc='Männlich']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement manGender;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Weiblich']"),
            @AndroidBy(xpath = "//*[@text='weiblich']"),
            @AndroidBy(xpath = "//*[@content-desc='Weiblich']"),
            @AndroidBy(xpath = "//*[@content-desc='weiblich']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement womanGender;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='ja']"),
            @AndroidBy(xpath = "//*[@text='Ja']"),
            @AndroidBy(xpath = "//*[@content-desc='ja']/android.widget.TextView"),
            @AndroidBy(xpath = "//*[@content-desc='Ja']/android.widget.TextView"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement yesButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='nein']"),
            @AndroidBy(xpath = "//*[@text='Nein']"),
            @AndroidBy(xpath = "//*[@content-desc='nein']/android.widget.TextView"),
            @AndroidBy(xpath = "//*[@content-desc='Nein']/android.widget.TextView"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement noButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-upload_nachweise.upload_files']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement uploadProofTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@content-desc='Ledig']"),
            @AndroidBy(xpath = "//*[@text='Ledig']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement single;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@content-desc='Verheiratet']"),
            @AndroidBy(xpath = "//*[@text='Verheiratet']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement married;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-antragstellende_person.antragsteller_titel']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement title;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Dr."),
            @AndroidBy(xpath = "//*[@content-desc='Dr.']/android.widget.TextView"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement profDrTitle;


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
            @AndroidBy(xpath = "//*[@content-desc=\"Senden\"]"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement sendButton;


    public HouseRegistrationFormularScreen(WebDriver driver) {
        super(driver);
    }


    public HouseRegistrationFormularScreen livingInGermany() {
        waitUntilVisible(registrationTypeTextField).click();
        waitUntilVisible(yesButton).click();
        return this;
    }


    public HouseRegistrationFormularScreen hasCompleteEducation() {
        waitUntilVisible(ummeldung).click();
        waitUntilVisible(noButton).click();
        return this;
    }

    public HouseRegistrationFormularScreen cancelDataImport() {
        waitUntilClickable(cancelButton).click();
        return this;
    }

    public HouseRegistrationFormularScreen fillEWAForm() {
        waitUntilClickable(registrationTypeTextField).click();
        waitUntilVisible(anmeldung);
        waitUntilClickable(ummeldung).click();

        dippAssertions.assertThat(waitUntilVisible(lastNameTextField).getAttribute(TEXT))
                .as("Applicant last name is not automatically filled")
                .isEqualTo("VON DREBENBUSCH-DALGOẞEN");

        WebElement element = waitUntilClickable(birthNameTextField);
        typeMobile(element, "Weiß");

        dippAssertions.assertThat(waitUntilVisible(firstNameTextField).getAttribute(TEXT))
                .as("Applicant first name is not automatically filled")
                .isEqualTo("HANS-GÜNTHER");

        dippAssertions.assertThat(mobileScrollDownUntilElementAppears(drTitleTextField).getAttribute(TEXT))
                .as("Applicant dr title is not automatically filled")
                .isEqualTo("DR. EH. DR.");

        dippAssertions.assertThat(mobileScrollUpAndDownUntilElementAppears(birthDateTextField).getAttribute(TEXT))
                .as("Applicant birthdate is not automatically filled")
                .isEqualTo("25.01.1946");

        dippAssertions.assertThat(mobileScrollUpAndDownUntilElementAppears(birthPlaceTextField).getAttribute(TEXT))
                .as("Applicant birth place is not automatically filled")
                .isEqualTo("HAMBURG");

        element = mobileScrollUpAndDownUntilElementAppears(birthLandTextField);
        typeMobile(element, "Deutschland");

        // mobileScrollUpAndDownUntilElementAppears(genderTextField).click();
        //clickIfPresent(manGender, NumericConstants.NUMERIC_4);
        element = mobileScrollUpAndDownUntilElementAppears(citizenshipTextField);
        typeMobile(element, "Deutsch");
        mobileScrollUpAndDownUntilElementAppears(familyStatusTextField).click();
        clickIfPresent(single, NumericConstants.NUMERIC_4);
        element = mobileScrollUpAndDownUntilElementAppears(religionTextField);
        typeMobile(element, "Christ");
        mobileScrollUpAndDownUntilElementAppears(idTypeTextField).click();
        clickIfPresent(idCard, NumericConstants.NUMERIC_4);
        element = mobileScrollUpAndDownUntilElementAppears(idCardNumberTextField);
        typeMobile(element, "LH1253436");

        dippAssertions.assertThat(mobileScrollUpAndDownUntilElementAppears(idCardIssuedOfficeTextField).getAttribute(TEXT))
                .as("Id Card issued office is not automatically filled")
                .isEqualTo("Governikus");

        element = mobileScrollUpAndDownUntilElementAppears(idCardIssuedDateTextField);
        typeMobile(element, "10.11.2015");

        dippAssertions.assertThat(mobileScrollUpAndDownUntilElementAppears(idCardValidityDateTextField).getAttribute(TEXT))
                .as("Id Card issued validity date is not automatically filled")
                .isEqualTo("30.06.2035");

        dippAssertions.assertThat(mobileScrollUpAndDownUntilElementAppears(formerStreetTextField).getAttribute(TEXT))
                .as("Old street is not automatically filled")
                .isEqualTo("WEG NR. 12");

        dippAssertions.assertThat(mobileScrollUpAndDownUntilElementAppears(formerHouseNumberTextField).getAttribute(TEXT))
                .as("Old house number is not automatically filled")
                .isEqualTo("8E");

        dippAssertions.assertThat(mobileScrollUpAndDownUntilElementAppears(formerPostCodeTextField).getAttribute(TEXT))
                .as("Old postcode is not automatically filled")
                .isEqualTo("22043");

        dippAssertions.assertThat(mobileScrollUpAndDownUntilElementAppears(formerCityTextField).getAttribute(TEXT))
                .as("Old postcode is not automatically filled")
                .isEqualTo("HAMBURG");

        element = mobileScrollUpAndDownUntilElementAppears(formerParkInDateTextField);
        typeMobile(element, "20.12.2010");
        element = mobileScrollUpAndDownUntilElementAppears(newStreetTextField);
        typeMobile(element, "Luckeweg");
        element = mobileScrollUpAndDownUntilElementAppears(newHouseNumberTextField);
        typeMobile(element, "200");
        element = mobileScrollUpAndDownUntilElementAppears(newPostcodeTextField);
        typeMobile(element, "12279");
        element = mobileScrollUpAndDownUntilElementAppears(newCityTextField);
        typeMobile(element, "Berlin");
        element = mobileScrollUpAndDownUntilElementAppears(cityPartTextField);
        typeMobile(element, "Tempelhof");
        element = mobileScrollUpAndDownUntilElementAppears(flatNumberTextField);
        typeMobile(element, "Erdgeschoss");
        element = mobileScrollUpAndDownUntilElementAppears(additionalInfoTextField);
        typeMobile(element, "1 Re");
        element = mobileScrollUpAndDownUntilElementAppears(livingWithTextField);
        typeMobile(element, "Müller Mike");
        element = mobileScrollUpAndDownUntilElementAppears(newParkInDateTextField);
        typeMobile(element, "10.06.2026");
        mobileScrollUpAndDownUntilElementAppears(parkOutDateAndParkInDateSameTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(houseOwnerTextField).click();
        clickIfPresent(yesTheHouseOwner, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(confirmHouseOwnership).click();
        clickIfPresent(confirmBestaetigt, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(movingInWithKidsTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);
        element = mobileScrollUpAndDownUntilElementAppears(numberOfKidsTextField);
        typeMobile(element, "2");
        element = mobileScrollUpAndDownUntilElementAppears(namesOfTheKidsTextField);
        typeMobile(element, "Leo Günther, Mary Günther ");
        element = mobileScrollUpAndDownUntilElementAppears(birthDateOfTheKidsTextField);
        typeMobile(element, "13 September 2020, 19 December 2023");
        mobileScrollUpAndDownUntilElementAppears(acceptIdCardUpdateTextField).click();
        clickIfPresent(confirmedErteilt, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(acceptEmailTransactionTextField).click();
        clickIfPresent(confirmedErteilt, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(declineReligionInfoTransferTextField).click();
        clickIfPresent(declineInfoTransfer, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(declineAgeAndMarriageJubileeTextField).click();
        clickIfPresent(declineInfoTransfer, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(declinePartyAndElectionTextField).click();
        clickIfPresent(declineInfoTransfer, NumericConstants.NUMERIC_4);

        return this;
    }
}
