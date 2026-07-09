package com.verimi.testcommon.pageobject.mobile;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdultChildSupportTutorialScreen extends MobileScreen {


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Deutschland-App']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement deutschlandAppText;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='vonDrebenbuschDalgossenHansG19460125']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement drebenbuschEid;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Bürgerservice']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Kindergeld ab 18 beantragen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//android.widget.TextView[@text='Weiter']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement nextButton;


    @AndroidFindBy(accessibility = "Nutzungsbedingungen, link, durch doppeltippen verlassen sie die app")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeLink[@name='Nutzungsbedingungen']")
    private WebElement termsAndConditionsLink;


    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Nach oben']")
    @iOSXCUITFindBy(accessibility = "Zurück")
    private WebElement backButton;


    public AdultChildSupportTutorialScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(screenTitle);
    }

    public void openDevMenu() {
        Point elementPosition = getElementCenterPoint(waitUntilVisible(deutschlandAppText));
        tapAtCoordinates(elementPosition);
        tapAtCoordinates(elementPosition);
        tapAtCoordinates(elementPosition);
        int attempt = 0;
        while (!isElementDisplayedWithWait(drebenbuschEid, NumericConstants.NUMERIC_5) && attempt++ < 4) {
            tapAtCoordinates(elementPosition);
            tapAtCoordinates(elementPosition);
            tapAtCoordinates(elementPosition);
            attempt++;
        }
    }

    public void selectEid() {
        waitUntilClickable(drebenbuschEid).click();

    }

    public void navigateBack() {
        waitUntilClickable(backButton).click();

    }

    public AdultChildSupportTutorialScreen activateMockEid() {
        openDevMenu();
        selectEid();
        navigateBack();
        return this;
    }


    private Point getElementCenterPoint(WebElement element) {
        Point location = element.getLocation();
        int x = location.getX();
        int y = location.getY();
        log.info("Element location: " + "X point: " + x + ", Y point: " + y);
        return new Point(x, y);
    }

    public HouseRegistrationTutorialScreen clickNextButton() {
        waitUntilClickable(nextButton).click();
        return new HouseRegistrationTutorialScreen(driver);
    }

    public HouseRegistrationTutorialScreen navigateToHouseRegistrationOnboardingScreen() {
        clickNextButton();
        return new HouseRegistrationTutorialScreen(driver);
    }

}
