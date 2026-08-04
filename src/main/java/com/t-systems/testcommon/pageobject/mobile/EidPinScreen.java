package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class EidPinScreen extends MobileScreen {

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
            @AndroidBy(xpath = "//android.view.View[@content-desc=\"PIN: leeres\"]/android.view.View[1]"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement pinField1;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Erneut versuchen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement retryButton;

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

    public EidPinScreen(WebDriver driver) {
        super(driver);
    }

    public EidScreen addEidCardPin(String pin) {
        int retry = 0;
        while (isElementDisplayedWithWait(retryButton, NumericConstants.NUMERIC_6) && retry < 5) {
            retryButton.click();
            waitUntilClickable(nextButton).click();
            retry++;
        }
        waitUntilClickable(pinField1).click();
        typePIN(pin);
        waitUntilClickable(nextButton).click();
        return new EidScreen(driver);
    }
    public EidPinScreen typePIN(String pinCode) {
        new Actions(driver)
                .sendKeys(pinCode)
                .perform();
        return new EidPinScreen(driver);
    }


}
