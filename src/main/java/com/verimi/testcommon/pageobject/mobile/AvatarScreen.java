package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AvatarScreen extends MobileScreen {

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
            @AndroidBy(xpath = "//*[@resource-id='avatar-view']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement avatarView;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='voice-record-button']"),
            @AndroidBy(xpath = "//*[@content-desc='Tippen, um zu sprechen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement micButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='voice-attach-button']"),
            @AndroidBy(xpath = "//*[@content-desc='Anhang']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement attachButton;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='com.android.permissioncontroller:id/permission_icon']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement permissionIcon;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement givePermission;

     @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='alert-dismiss-button']"),
             @AndroidBy(xpath = "//*[@text='Schließen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement closePermissionAlert;

    public AvatarScreen(WebDriver driver) {
        super(driver);
    }


    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(avatarView);
    }


    public AvatarScreen assertAvatarScreenContent() {
        log.info("Asserting contents on the screen");

        dippAssertions.assertThat(isElementDisplayedWithWait(chatButton, NumericConstants.NUMERIC_10))
                .as("Chat button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(formularButton, NumericConstants.NUMERIC_10))
                .as("Form button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(avatarButton, NumericConstants.NUMERIC_10))
                .as("Avatar button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(avatarView, NumericConstants.NUMERIC_10))
                .as("Avatar view should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(micButton, NumericConstants.NUMERIC_10))
                .as("Mic button should be displayed")
                .isTrue();

        dippAssertions.assertThat(isElementDisplayedWithWait(attachButton, NumericConstants.NUMERIC_10))
                .as("Attachment button should be displayed")
                .isTrue();

        return this;
    }

    public AvatarScreen checkMic() {
        waitUntilClickable(micButton).click();
        waitUntilClickable(givePermission).click();
        waitUntilClickable(closePermissionAlert).click();
        waitUntilVisible(avatarView);
        return this;
    }

    public AttachmentScreen navigateToAttachmentScreen() {
        waitUntilClickable(attachButton).click();
        return new AttachmentScreen(driver);
    }

}
