package com.tsys.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tsys.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttachmentScreen extends MobileScreen {



  @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='document-upload-close']"),
            @AndroidBy(xpath = "//*[@content-desc='Dokumentenauswahl schließen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement closeXButton;


  @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='attachment-option-take-photo']"),
            @AndroidBy(xpath = "//*[@content-desc='Foto aufnehmen. Öffnet die Kamera']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement takePhoto;

  @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='attachment-option-photo-library']"),
            @AndroidBy(xpath = "//*[@content-desc='Foto Mediathek. Öffnet die Foto Mediathek']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement photoMedia;


  @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='attachment-option-choose-file']"),
            @AndroidBy(xpath = "//*[@content-desc='Datei auswählen. Öffnet die Dateien Übersicht']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement selectData;


  @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='attachment-option-documents']"),
            @AndroidBy(xpath = "//*[@content-desc='Dokumente. Aus dem Sicheren Speicher']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement document;

    public AttachmentScreen(WebDriver driver) {
        super(driver);
    }


    @Override
    public void waitUntilPageLoads() {
    }


    public AttachmentScreen assertAttachmentScreenContent() {
        log.info("Asserting contents on the screen");

        dippAssertions.assertThat(isElementDisplayedWithWait(closeXButton, NumericConstants.NUMERIC_10))
                .as("Close button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(takePhoto, NumericConstants.NUMERIC_10))
                .as("Take photo button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(photoMedia, NumericConstants.NUMERIC_10))
                .as("Foto media button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(selectData, NumericConstants.NUMERIC_10))
                .as("Select data button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(document, NumericConstants.NUMERIC_10))
                .as("Document button should be displayed")
                .isTrue();
        return this;
    }


    public AvatarScreen navigateBackToAvatar() {
        waitUntilClickable(closeXButton).click();
        return new AvatarScreen(driver);
    }

}
