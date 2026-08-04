package com.verimi.testcommon.pageobject.mobile;

import static com.verimi.testcommon.model.common.accessibility.androidaccessibility.AndroidAccessibilityMobleElement.TEXT;

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
public class AdultChildSupportFormularScreen extends com.verimi.testcommon.pageobject.mobile.MobileScreen {

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
            @AndroidBy(xpath = "//*[@resource-id='select-auswahl.antragstellerangaben']"),
            @AndroidBy(accessibility = "Wohnen Sie in Deutschland?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Wohnen Sie in Deutschland?, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement liveInGermanyTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-auswahl.kindangabenkeinausbildung']"),
            @AndroidBy(accessibility = "Hat das Kind eine abgeschlossene Erstausbildung?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Hat das Kind eine abgeschlossene Erstausbildung?, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement finishedEducationalTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-auswahl.kind_sachverhalt']"),
            @AndroidBy(accessibility = "Sachverhalt nach Eintritt der Volljährigkeit, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Sachverhalt nach Eintritt der Volljährigkeit, Auswählen...']"),
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
            @AndroidBy(xpath = "//*[@resource-id='select-auslandsbezug.zuerbezugvorhanden']"),
            @AndroidBy(accessibility = "Auslandsbezug vorhanden?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Auslandsbezug vorhanden?, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement abroadBenefitTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-auslandsbezug.zuerwohnhaft']"),
            @AndroidBy(accessibility = "Ehegatte/anderer Elternteil wohnt außerhalb Deutschlands?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Ehegatte/anderer Elternteil wohnt außerhalb Deutschlands?, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement partnerTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_name_vorname']"),
            @AndroidBy(accessibility = "Vorname"),
            @AndroidBy(xpath = "//*[@content-desc='Vorname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childFirstNameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_name_nachname']"),
            @AndroidBy(accessibility = "Familienname"),
            @AndroidBy(xpath = "//*[@content-desc='Familienname']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childLastNameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='date-picker-angaben_kind.ba_datumsgruppe_0-input']"),
            @AndroidBy(xpath = "//*[@resource-id='date-picker-angaben_kind.ba_datumsgruppe_0']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childBirthdateTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.buttongroup_geschlecht_kind']"),
            @AndroidBy(accessibility = "Geschlecht, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Geschlecht, Auswählen...']"),
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
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.buttongroup_kindschaftsverhaeltnis_kind']"),
            @AndroidBy(accessibility = "Kindschaftsverhältnis, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Kindschaftsverhältnis, Auswählen...']"),
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
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_staatsangehoerigkeit']"),
            @AndroidBy(accessibility = "Staatsangehörigkeit"),
            @AndroidBy(xpath = "//*[@content-desc='Staatsangehörigkeit']"),
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
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_steuerid']"),
            @AndroidBy(accessibility = "Identifikationsnummer"),
            @AndroidBy(xpath = "//*[@content-desc='Identifikationsnummer']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement identificationNumberTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_has_no_steuerid']"),
            @AndroidBy(accessibility = "Identifikationsnummer ist nicht vorhanden, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Identifikationsnummer ist nicht vorhanden, Auswählen...']"),
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
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_abweichende_adresse']"),
            @AndroidBy(accessibility = "Das Kind wohnt nicht in meinem Haushalt, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Das Kind wohnt nicht in meinem Haushalt, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childNotLivingInMyHouseTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_abweichende_adresse_grund']"),
            @AndroidBy(accessibility = "Grund für abweichende Wohnadresse des Kindes, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Grund für abweichende Wohnadresse des Kindes, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement reasonForNotLivingInMyHouseTextField;


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
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_anspruchab18_studium_beginnstudiummonat']"),
            @AndroidBy(accessibility = "Beginn des Studiums - Monat..."),
            @AndroidBy(xpath = "//*[@content-desc='Beginn des Studiums - Monat...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement monthStudyBeginTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_anspruchab18_studium_beginnstudiumjahr']"),
            @AndroidBy(accessibility = "Beginn des Studiums - Jahr..."),
            @AndroidBy(xpath = "//*[@content-desc='Beginn des Studiums - Jahr...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement yearStudyBeginTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-angaben_kind.kind_anspruchab18_angabenzustudium_studiengang']"),
            @AndroidBy(accessibility = "Studiengang"),
            @AndroidBy(xpath = "//*[@content-desc='Studiengang']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement studyDisciplineTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_anspruchab18_studium_endestudiummonat']"),
            @AndroidBy(accessibility = "Voraussichtliches Ende des Studiums - Monat..."),
            @AndroidBy(xpath = "//*[@content-desc='Voraussichtliches Ende des Studiums - Monat...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement monthStudyEndTextField;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-angaben_kind.kind_anspruchab18_studium_endestudiumjahr']"),
            @AndroidBy(accessibility = "Voraussichtliches Ende des Studiums - Jahr..."),
            @AndroidBy(xpath = "//*[@content-desc='Voraussichtliches Ende des Studiums - Jahr...']"),
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
            @AndroidBy(xpath = "//*[@resource-id='date-picker-antragstellende_person.ba_datumsgruppe_2-input']"),
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
            @AndroidBy(xpath = "//*[@resource-id='select-antragstellende_person.antragsteller_adresse_wohnland']"),
            @AndroidBy(accessibility = "Wohnland, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Wohnland, Auswählen...']"),
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
            @AndroidBy(xpath = "//*[@resource-id='select-antragstellende_person.antragsteller_titel']"),
            @AndroidBy(accessibility = "Titel, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Titel, Auswählen...']"),
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
            @AndroidBy(xpath = "//*[@resource-id='select-anderer_elternteil.elternteil_unbekannt']"),
            @AndroidBy(accessibility = "Anderer Elternteil unbekannt, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Anderer Elternteil unbekannt, Auswählen...']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement otherParentUnknownTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-anderer_elternteil.elternteil_verstorben']"),
            @AndroidBy(accessibility = "Anderer Elternteil verstorben, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Anderer Elternteil verstorben, Auswählen...']"),

    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement otherParentDeadTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-zahlungsweg.antragsteller_bankdaten_abweichendekontoinhabendeperson']"),
            @AndroidBy(accessibility = "Abweichende kontoinhabende Person"),
            @AndroidBy(xpath = "//*[@content-desc='Abweichende kontoinhabende Person']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement differentAccountOwnerTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-zahlungsweg.antragsteller_bankdaten_iban']"),
            @AndroidBy(accessibility = "IBAN"),
            @AndroidBy(xpath = "//*[@content-desc='IBAN']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement ibanTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-zahlungsweg.antragsteller_bankdaten_bic']"),
            @AndroidBy(accessibility = "BIC"),
            @AndroidBy(xpath = "//*[@content-desc='BIC']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement bicTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='text-field-zahlungsweg.antragsteller_bankdaten_kreditinstitut']"),
            @AndroidBy(accessibility = "Kreditinstitut"),
            @AndroidBy(xpath = "//*[@content-desc='Kreditinstitut']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement bankNameTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-vorbezug.vorbezug_hasvorbezug']"),
            @AndroidBy(accessibility = "Hat ein Vorbezug stattgefunden?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Hat ein Vorbezug stattgefunden?, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement childSupportGottenBeforeTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-oeffentlicher_dienst.imoeffentlichendiensttaetig']"),
            @AndroidBy(accessibility = "Im öffentlichen Dienst tätig?, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Im öffentlichen Dienst tätig?, Auswählen...']"),
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
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement furtherInfoTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.directtransmittedantragab18']"),
            @AndroidBy(accessibility = "Bestätigung elektronische Übermittlung, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Bestätigung elektronische Übermittlung, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement confirmElectronicTransferTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.acknowledgedberechtigenbestimmung']"),
            @AndroidBy(accessibility = "Zustimmung Berechtigtenbestimmung, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Zustimmung Berechtigtenbestimmung, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement confirmRight;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.datahandledantragab18']"),
            @AndroidBy(accessibility = "Datenschutzhinweis Verarbeitung, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Datenschutzhinweis Verarbeitung, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement dataProtectionTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.datacorrectantragab18']"),
            @AndroidBy(accessibility = "Richtigkeit der Angaben, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Richtigkeit der Angaben, Auswählen...']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement infoCorrectnessTextField;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='select-zusammenfassung.datenschutz']"),
            @AndroidBy(accessibility = "Kenntnisnahme Merkblatt und Datenschutz, Auswählen..."),
            @AndroidBy(xpath = "//*[@content-desc='Kenntnisnahme Merkblatt und Datenschutz, Auswählen...']"),
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
            @AndroidBy(xpath = "//*[@text=\"Senden\"]"),
            @AndroidBy(xpath = "//*[@content-desc=\"Senden\"]"),
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
        clickIfPresent(noButton, NumericConstants.NUMERIC_4);

        // Child Info
        WebElement element;
        element = mobileScrollUpAndDownUntilElementAppears(childFirstNameTextField);
        typeMobile(element, userData.getChildFirstName());
        element = mobileScrollUpAndDownUntilElementAppears(childLastNameTextField);
        typeMobile(element, userData.getChildLastName());
        element = mobileScrollUpAndDownUntilElementAppears(childBirthdateTextField);
        typeMobile(element, "12.04.2006");

        mobileScrollUpAndDownUntilElementAppears(childGenderTextField).click();
        clickIfPresent(manGender, NumericConstants.NUMERIC_4);

        mobileScrollUpAndDownUntilElementAppears(relationshipToChildTextField).click();
        clickIfPresent(biologicalChild, NumericConstants.NUMERIC_4);
        element = mobileScrollUpAndDownUntilElementAppears(citizenshipTextField);
        typeMobile(element, userData.getIdentificationNumber());
        mobileScrollUpAndDownUntilElementAppears(identificationNumberNotAvailableTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);

        mobileScrollUpAndDownUntilElementAppears(childNotLivingInMyHouseTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(reasonForNotLivingInMyHouseTextField).click();
        clickIfPresent(study, NumericConstants.NUMERIC_4);


        // Applicant info
        element = mobileScrollUpAndDownUntilElementAppears(applicantBirthDateTextField);
        dippAssertions.assertThat(element.getAttribute(TEXT))
                .as("Applicant birthdate is not automatically filled")
                .isEqualTo("25.01.1946");

        mobileScrollUpAndDownUntilElementAppears(applicantGenderTextField).click();
        clickIfPresent(manGender, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(familyStatusTextField).click();
        clickIfPresent(single, NumericConstants.NUMERIC_4);

        mobileScrollUpAndDownUntilElementAppears(countryTextField).click();
        clickIfPresent(germany, NumericConstants.NUMERIC_4);

        //mobileScrollUpAndDownUntilElementAppears(title).click();
        //mobileScrollDownUntilElementAppears(profDrtitle).click();
        if (isElementDisplayedWithWait(applicantFirstNameTextField, NumericConstants.NUMERIC_3)) {
            element = mobileScrollUpAndDownUntilElementAppears(applicantFirstNameTextField);
            typeMobile(element, userData.getApplicantFirstName());
            element = mobileScrollUpAndDownUntilElementAppears(applicantLastNameTextField);
            typeMobile(element, userData.getApplicantLastName());
            element = mobileScrollUpAndDownUntilElementAppears(postCodeTextField);
            typeMobile(element, userData.getPoctcode());
            element = mobileScrollUpAndDownUntilElementAppears(cityTextField);
            typeMobile(element, userData.getCity());
            element = mobileScrollUpAndDownUntilElementAppears(streetTextField);
            typeMobile(element, userData.getStreetAddress());
            element = mobileScrollUpAndDownUntilElementAppears(houseNumberTextField);
            typeMobile(element, userData.getHouseNumber());
            element = mobileScrollUpAndDownUntilElementAppears(additionalAddressTextField);
            typeMobile(element, userData.getAdditionalAddress());
            element = mobileScrollUpAndDownUntilElementAppears(telePhoneCodeTextField);
            typeMobile(element, userData.getPhoneCode());
            element = mobileScrollUpAndDownUntilElementAppears(telePhoneNumberTextField);
            typeMobile(element, userData.getPhoneNumber());
            element = mobileScrollUpAndDownUntilElementAppears(identificationNumberTextField);
            typeMobile(element, userData.getIdentificationNumber());
        }


        // Other parent info
        mobileScrollUpAndDownUntilElementAppears(otherParentUnknownTextField).click();
        clickIfPresent(noButton, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(otherParentDeadTextField).click();
        clickIfPresent(noButton, NumericConstants.NUMERIC_4);


        element = mobileScrollUpAndDownUntilElementAppears(differentAccountOwnerTextField);
        typeMobile(element, userData.getOtherParent().getFirstName() + " " + userData.getOtherParent().getLastName());
        element = mobileScrollUpAndDownUntilElementAppears(ibanTextField);
        typeMobile(element, userData.getOtherParent().getIban());
        element = mobileScrollUpAndDownUntilElementAppears(bicTextField);
        typeMobile(element, userData.getOtherParent().getBic());
        element = mobileScrollUpAndDownUntilElementAppears(bankNameTextField);
        typeMobile(element, userData.getOtherParent().getBank());

        mobileScrollUpAndDownUntilElementAppears(childSupportGottenBeforeTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);

        mobileScrollUpAndDownUntilElementAppears(workingInPublicSectorTextField).click();
        clickIfPresent(noButton, NumericConstants.NUMERIC_4);

        mobileScrollUpAndDownUntilElementAppears(furtherInfoTextField).sendKeys(userData.getFurtherInfo());
        mobileScrollUpAndDownUntilElementAppears(confirmElectronicTransferTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(confirmRight).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(dataProtectionTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(infoCorrectnessTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);
        mobileScrollUpAndDownUntilElementAppears(merkblattAndDataProtectionTextField).click();
        clickIfPresent(yesButton, NumericConstants.NUMERIC_4);
        return this;
    }
}
