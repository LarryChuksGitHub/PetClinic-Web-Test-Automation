package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import com.verimi.testcommon.testdata.DeutschlandAppUserData;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdultChildSupportFormularScreen extends MobileScreen {

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
            @AndroidBy(accessibility = "Wohnen Sie in Deutschland?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Wohnen Sie in Deutschland?, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-auswahl.antragstellerangaben']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement liveInGermanyTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Hat das Kind eine abgeschlossene Erstausbildung?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Hat das Kind eine abgeschlossene Erstausbildung?, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-auswahl.kindangabenkeinausbildung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement finishedEducationalTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Sachverhalt nach Eintritt der Volljährigkeit, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Sachverhalt nach Eintritt der Volljährigkeit, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-auswahl.kind_sachverhalt']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement adultAgeEventTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@content-desc='Berufsausbildung']"),
            @AndroidBy(xpath = "//*[@text='Berufsausbildung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement ausbildung;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Auslandsbezug vorhanden?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Auslandsbezug vorhanden?, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-auslandsbezug.zuerbezugvorhanden']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement abroadBenefitTextField;

    @AndroidFindAll({
            @AndroidBy(accessibility = "Ehegatte/anderer Elternteil wohnt außerhalb Deutschlands?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Ehegatte/anderer Elternteil wohnt außerhalb Deutschlands?, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-auslandsbezug.zuerwohnhaft']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement partnerTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Vorname"),
            @AndroidBy(xpath = "//*[@content-desc='Vorname']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_name_vorname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childFirstNameTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Familienname"),
            @AndroidBy(xpath = "//*[@content-desc='Familienname']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_name_nachname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childLastNameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='date-picker-angaben_kind.ba_datumsgruppe_0']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childBirthdateTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Geschlecht, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Geschlecht, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='resource-id']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childGenderTextField;


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
            @AndroidBy(accessibility = "Kindschaftsverhältnis, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Kindschaftsverhältnis, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.buttongroup_kindschaftsverhaeltnis_kind']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement relationshipToChildTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='leibliches Kind']"),
            @AndroidBy(xpath = "//*[@content-desc='leibliches Kind']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement biologicalChild;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Staatsangehörigkeit"),
            @AndroidBy(xpath = "//*[@content-desc='Staatsangehörigkeit']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_staatsangehoerigkeit']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement citizenshipTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Kindergeldnummer"),
            @AndroidBy(xpath = "//*[@content-desc='Kindergeldnummer']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childSupportNumberTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Identifikationsnummer"),
            @AndroidBy(xpath = "//*[@content-desc='Identifikationsnummer']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_steuerid']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement identificationNumberTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Identifikationsnummer ist nicht vorhanden, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Identifikationsnummer ist nicht vorhanden, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_has_no_steuerid']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement identificationNumberNotAvailableTextField;


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
            @AndroidBy(accessibility = "Das Kind wohnt nicht in meinem Haushalt, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Das Kind wohnt nicht in meinem Haushalt, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_abweichende_adresse']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childNotLivingInMyHouseTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Grund für abweichende Wohnadresse des Kindes, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Grund für abweichende Wohnadresse des Kindes, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_abweichende_adresse_grund']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement reasonForNotLivingInMyHouseTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Grund für abweichende Wohnadresse des Kindes, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Grund für abweichende Wohnadresse des Kindes, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-upload_nachweise.upload_files']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement uploadProofTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@content-desc='Ausbildung_Studium']/android.widget.TextView"),
            @AndroidBy(xpath = "//*[@text='Ausbildung_Studium']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement study;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Beginn des Studiums - Monat..."),
            @AndroidBy(xpath = "//*[@content-desc='Beginn des Studiums - Monat...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_anspruchab18_studium_beginnstudiummonat']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement monthStudyBeginTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Beginn des Studiums - Jahr..."),
            @AndroidBy(xpath = "//*[@content-desc='Beginn des Studiums - Jahr...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_anspruchab18_studium_beginnstudiumjahr']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement yearStudyBeginTextField;

    @AndroidFindAll({
            @AndroidBy(accessibility = "Studiengang"),
            @AndroidBy(xpath = "//*[@content-desc='Studiengang']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_anspruchab18_angabenzustudium_studiengang']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement studyDisciplineTextField;

    @AndroidFindAll({
            @AndroidBy(accessibility = "Voraussichtliches Ende des Studiums - Monat..."),
            @AndroidBy(xpath = "//*[@content-desc='Voraussichtliches Ende des Studiums - Monat...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_anspruchab18_studium_endestudiummonat']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement monthStudyEndTextField;

    @AndroidFindAll({
            @AndroidBy(accessibility = "Voraussichtliches Ende des Studiums - Jahr..."),
            @AndroidBy(xpath = "//*[@content-desc='Voraussichtliches Ende des Studiums - Jahr...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_anspruchab18_studium_endestudiumjahr']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement yearStudyEndTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Vorname"),
            @AndroidBy(xpath = "//*[@content-desc='Vorname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement applicantFirstNameTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Familienname"),
            @AndroidBy(xpath = "//*[@content-desc='Familienname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement applicantLastNameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='date-picker-antragstellende_person.ba_datumsgruppe_2']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement applicantBirthDateTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-antragstellende_person.buttongroup_geschlecht_antragsteller']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement applicantGenderTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-antragstellende_person.antragsteller_familienstand']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement familyStatusTextField;


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
            @AndroidBy(accessibility = "Postleitzahl"),
            @AndroidBy(xpath = "//*[@content-desc='Postleitzahl']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement postCodeTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Ort"),
            @AndroidBy(xpath = "//*[@content-desc='Ort']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement cityTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Straße"),
            @AndroidBy(xpath = "//*[@content-desc='Straße']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement streetTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Hausnummer"),
            @AndroidBy(xpath = "//*[@content-desc='Hausnummer']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement houseNumberTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Wohnland, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Wohnland, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-antragstellende_person.antragsteller_adresse_wohnland']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement countryTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@content-desc='Deutschland']"),
            @AndroidBy(xpath = "//*[@text='Deutschland']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement germany;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Adresszusatz"),
            @AndroidBy(xpath = "//*[@content-desc='Adresszusatz']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement additionalAddressTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Vorwahl"),
            @AndroidBy(xpath = "//*[@content-desc='Vorwahl']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement telePhoneCodeTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Rufnummer"),
            @AndroidBy(xpath = "//*[@content-desc='Rufnummer']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement telePhoneNumberTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Titel, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Titel, Auswählen...']"),
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
    private WebElement profDrtitle;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Anderer Elternteil unbekannt, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Anderer Elternteil unbekannt, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-anderer_elternteil.elternteil_unbekannt']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement otherParentUnknownTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Anderer Elternteil verstorben, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Anderer Elternteil verstorben, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-anderer_elternteil.elternteil_verstorben']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement otherParentDeadTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Abweichende kontoinhabende Person"),
            @AndroidBy(xpath = "//*[@content-desc='Abweichende kontoinhabende Person']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-zahlungsweg.antragsteller_bankdaten_abweichendekontoinhabendeperson']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement differentAccountOwnerTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "IBAN"),
            @AndroidBy(xpath = "//*[@content-desc='IBAN']"),
            @AndroidBy(xpath = "//*[@resource-id='resource-id']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement ibanTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "BIC"),
            @AndroidBy(xpath = "//*[@content-desc='BIC']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-zahlungsweg.antragsteller_bankdaten_bic']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement bicTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Kreditinstitut"),
            @AndroidBy(xpath = "//*[@content-desc='Kreditinstitut']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-zahlungsweg.antragsteller_bankdaten_kreditinstitut']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement bankNameTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Hat ein Vorbezug stattgefunden?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Hat ein Vorbezug stattgefunden?, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-vorbezug.vorbezug_hasvorbezug']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childSupportGottenBeforeTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Im öffentlichen Dienst tätig?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Im öffentlichen Dienst tätig?, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-oeffentlicher_dienst.imoeffentlichendiensttaetig']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement workingInPublicSectorTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-weitere_angaben.weitere_angaben_text']"),
            @AndroidBy(xpath = "//*[@content-desc='Weitere Angaben']"),
            @AndroidBy(xpath = "//*[@resource-id='text-field-weitere_angaben.weitere_angaben_text']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement furtherInfoTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Bestätigung elektronische Übermittlung, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Bestätigung elektronische Übermittlung, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.directtransmittedantragab18']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement confirmElectronicTransferTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Zustimmung Berechtigtenbestimmung, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Zustimmung Berechtigtenbestimmung, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.acknowledgedberechtigenbestimmung']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement confirmRight;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Datenschutzhinweis Verarbeitung, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Datenschutzhinweis Verarbeitung, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.datahandledantragab18']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement dataProtectionTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Richtigkeit der Angaben, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Richtigkeit der Angaben, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.datacorrectantragab18']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement infoCorrectnessTextField;


    @AndroidFindAll({
            @AndroidBy(accessibility = "Kenntnisnahme Merkblatt und Datenschutz, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Kenntnisnahme Merkblatt und Datenschutz, Auswählen...']"),
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.datenschutz']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement merkblattAndDataProtectionTextField;


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


    public AdultChildSupportFormularScreen(WebDriver driver) {
        super(driver);
    }


    public AdultChildSupportFormularScreen livingInGermany() {
        waitUntilVisible(liveInGermanyTextField).click();
        waitUntilVisible(yesButton).click();
        return this;
    }


    public AdultChildSupportFormularScreen hasCompleteEducation() {
        waitUntilVisible(finishedEducationalTextField).click();
        waitUntilVisible(noButton).click();
        return this;
    }


    public AdultChildSupportFormularScreen adultAgeEvent() {
        waitUntilVisible(adultAgeEventTextField).click();
        waitUntilVisible(noButton).click();
        return this;
    }

    public AdultChildSupportFormularScreen abroadEvent() {
        waitUntilVisible(abroadBenefitTextField).click();
        waitUntilVisible(noButton).click();
        return this;
    }

    public AdultChildSupportFormularScreen study() {
        mobileScrollDownUntilElementIsVisible(ausbildung);
        waitUntilVisible(ausbildung).click();
        return this;
    }

    public AdultChildSupportFormularScreen cancelDataImport() {
        waitUntilClickable(cancelButton).click();
        return this;
    }

    public AdultChildSupportFormularScreen fillCompleteForm() {
        DeutschlandAppUserData userData = DeutschlandAppUserData.getUserData();
        waitUntilClickable(liveInGermanyTextField).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        waitUntilClickable(finishedEducationalTextField).click();
        mobileScrollDownUntilElementAppears(noButton).click();
        waitUntilClickable(adultAgeEventTextField).click();
        mobileScrollDownUntilElementAppears(ausbildung).click();
        mobileScrollUpAndDownUntilElementAppears(abroadBenefitTextField).click();
        mobileScrollDownUntilElementAppears(noButton).click();
        mobileScrollUpAndDownUntilElementAppears(partnerTextField).click();
        mobileScrollDownUntilElementAppears(noButton).click();

        // Child Info
        typeMobile(mobileScrollUpAndDownUntilElementAppears(childFirstNameTextField), userData.getChildFirstName());
        typeMobile(mobileScrollUpAndDownUntilElementAppears(childLastNameTextField), userData.getChildLastName());
        mobileScrollUpAndDownUntilElementAppears(childBirthdateTextField).click();
        waitUntilClickable(okButton).click();
        mobileScrollUpAndDownUntilElementAppears(childGenderTextField).click();
        mobileScrollDownUntilElementAppears(manGender).click();
        mobileScrollUpAndDownUntilElementAppears(relationshipToChildTextField).click();
        mobileScrollDownUntilElementAppears(biologicalChild).click();
        typeMobile(mobileScrollUpAndDownUntilElementAppears(citizenshipTextField), userData.getCitizenship());
        typeMobile(mobileScrollUpAndDownUntilElementAppears(identificationNumberTextField), userData.getIdentificationNumber());
        mobileScrollUpAndDownUntilElementAppears(identificationNumberNotAvailableTextField).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        mobileScrollUpAndDownUntilElementAppears(childNotLivingInMyHouseTextField).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        mobileScrollUpAndDownUntilElementAppears(reasonForNotLivingInMyHouseTextField).click();
        mobileScrollDownUntilElementAppears(study).click();

        // Applicant info
        WebElement element = mobileScrollUpAndDownUntilElementAppears(applicantBirthDateTextField);
        dippAssertions.assertThat(element.getAttribute(CONTENT_DESC_ATTRIBUTE))
                .as("Applicant birthdate is not automatically filled")
                .isEqualTo("Geburtsdatum, 25.1.1946");

        mobileScrollUpAndDownUntilElementAppears(applicantGenderTextField).click();
        mobileScrollDownUntilElementAppears(manGender).click();
        mobileScrollUpAndDownUntilElementAppears(familyStatusTextField).click();
        mobileScrollDownUntilElementAppears(single).click();
        mobileScrollUpAndDownUntilElementAppears(countryTextField).click();
        mobileScrollDownUntilElementAppears(germany).click();
        //mobileScrollUpAndDownUntilElementAppears(title).click();
        //mobileScrollDownUntilElementAppears(profDrtitle).click();
        if (isElementDisplayedWithWait(applicantFirstNameTextField, NumericConstants.NUMERIC_3)) {
            typeMobile(mobileScrollUpAndDownUntilElementAppears(applicantFirstNameTextField), userData.getApplicantFirstName());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(applicantLastNameTextField), userData.getApplicantLastName());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(postCodeTextField), userData.getPoctcode());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(cityTextField), userData.getCity());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(streetTextField), userData.getStreetAddress());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(houseNumberTextField), userData.getHouseNumber());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(additionalAddressTextField), userData.getAdditionalAddress());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(telePhoneCodeTextField), userData.getPhoneCode());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(telePhoneNumberTextField), userData.getPhoneNumber());
            typeMobile(mobileScrollUpAndDownUntilElementAppears(identificationNumberTextField), userData.getIdentificationNumber());
        }


        // Other parent info
        /*
        mobileScrollDownUntilElementAppears(otherParentsTextField).click();
        mobileScrollDownUntilElementAppears(noButton).click();
        mobileScrollDownUntilElementAppears(otherParentDeadTextField).click();
        mobileScrollDownUntilElementAppears(noButton).click();

         */
        mobileScrollUpAndDownUntilElementAppears(otherParentUnknownTextField).click();
        mobileScrollDownUntilElementAppears(noButton).click();
        mobileScrollUpAndDownUntilElementAppears(otherParentDeadTextField).click();
        mobileScrollDownUntilElementAppears(noButton).click();
        typeMobile(mobileScrollUpAndDownUntilElementAppears(differentAccountOwnerTextField), userData.getOtherParent().getFirstName() + " " + userData.getOtherParent().getLastName());
        typeMobile(mobileScrollUpAndDownUntilElementAppears(ibanTextField), userData.getOtherParent().getIban());
        typeMobile(mobileScrollUpAndDownUntilElementAppears(bicTextField), userData.getOtherParent().getBic());
        typeMobile(mobileScrollUpAndDownUntilElementAppears(bankNameTextField), userData.getOtherParent().getBank());

        mobileScrollUpAndDownUntilElementAppears(childSupportGottenBeforeTextField).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        mobileScrollUpAndDownUntilElementAppears(workingInPublicSectorTextField).click();
        mobileScrollDownUntilElementAppears(noButton).click();
        mobileScrollUpAndDownUntilElementAppears(furtherInfoTextField);
        typeMobile(mobileScrollUpAndDownUntilElementAppears(furtherInfoTextField), userData.getFurtherInfo());
        mobileScrollUpAndDownUntilElementAppears(confirmElectronicTransferTextField).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        mobileScrollUpAndDownUntilElementAppears(confirmRight).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        mobileScrollUpAndDownUntilElementAppears(dataProtectionTextField).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        mobileScrollUpAndDownUntilElementAppears(infoCorrectnessTextField).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        mobileScrollUpAndDownUntilElementAppears(merkblattAndDataProtectionTextField).click();
        mobileScrollDownUntilElementAppears(yesButton).click();
        return this;
    }
}
