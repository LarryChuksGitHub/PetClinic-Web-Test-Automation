package com.verimi.testcommon.pageobject.mobile;

import static com.verimi.testcommon.config.Config.LOAD_WAIT;
import static com.verimi.testcommon.config.Config.isAndroid;
import static com.verimi.testcommon.pageobject.mobile.OtpScreen.TSY_MAIL_SUBJECT;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.verimi.testcommon.framework.utils.constant.NumericConstants;
import com.verimi.testcommon.framework.utils.testhelper.TestContext;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailHogScreen extends com.verimi.testcommon.pageobject.mobile.MobileScreen {

    private static final Pattern OTP_PATTERN = Pattern.compile("\\b(\\d{6})\\b");


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Deutschland-App PIN']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
            @iOSXCUITBy(accessibility = "Einstellungen"),
            @iOSXCUITBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND (name == 'Settings' OR name == 'Einstellungen')")
    })
    private WebElement screenTitle;


    @AndroidFindAll({
            @AndroidBy(id = "com.android.chrome:id/search_box_text"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement searchBox;


    @AndroidFindAll({
            @AndroidBy(xpath ="//*[@resource-id='com.android.chrome:id/url_bar']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement portraitUrlBar;


    @AndroidFindAll({
            @AndroidBy(xpath ="//*[@resource-id='com.android.chrome:id/url_bar']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement landscapeSearchBox;


    @AndroidFindAll({
            @AndroidBy(id = "com.android.chrome:id/positive_button"),
            @AndroidBy(xpath = "//*[@text='Allow']"),
            @AndroidBy(xpath = "//*[@text='Zulassen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'positive_button')]"),
    })
    private WebElement acceptAlert;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='KByQx']"),
            @AndroidBy(xpath = "//*[@text='Weitere Informationen']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'positive_button')]"),
    })
    private WebElement furtherInfoButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@resource-id='L2AGLb']"),
            @AndroidBy(xpath = "//*[@text='Alle akzeptieren']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'positive_button')]"),
    })
    private WebElement acceptAllButton;


    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='" + TSY_MAIL_SUBJECT + "']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement subjectText;

    @AndroidFindAll({
            @AndroidBy(xpath = "//*[@text='Dieser einmalige Code ist nur für 20 Minuten gültig.']"),
    })
    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//*[contains(@label,'Einwilligungen')]"),
    })
    private WebElement oneTimeCode;


    public MailHogScreen(WebDriver driver) {
        super(driver);
    }


    public static void openMobileBrowser(WebDriver driver) {
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            try {
                androidDriver.activateApp(ANDROID_CHROME_PACKAGE);
            } catch (WebDriverException e) {
                androidDriver.activateApp(ANDROID_CHROME_PACKAGE);
            }
            androidDriver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            IOSDriver iosDriver = ((IOSDriver) driver);
            iosDriver.activateApp(IOS_SAFARI_PACKAGE);
        }

    }

    @Override
    public void waitUntilPageLoads() {
    }

    @SneakyThrows
    public String getOtpCode(String devMailHogUrl, String demoMailHogUrl, String emailAddress) {
        openMobileBrowser(driver);
        String pageSource = "";
        WebElement element;
        String xpath;
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(LOAD_WAIT));
            waitUntilClickable(searchBox).sendKeys(devMailHogUrl);
            androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
            if (isElementDisplayedWithWait(furtherInfoButton, NumericConstants.NUMERIC_12)) {
                furtherInfoButton.click();
                waitUntilClickable(acceptAllButton).click();
                waitUntilClickable(portraitUrlBar).click();
                portraitUrlBar.sendKeys(devMailHogUrl);
                androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
            }
            if (isElementDisplayedWithWait(acceptAlert, NumericConstants.NUMERIC_12)) {
                acceptAlert.click();
            }
            androidDriver.rotate(ScreenOrientation.LANDSCAPE);
            xpath = "//*[@text='" + emailAddress + "']";

            log.info(LOOKING_FOR_ELEMENT, xpath);
            if(isElementDisplayedWithWait(acceptAlert, NumericConstants.NUMERIC_12)) {
                acceptAlert.click();
            }

            if(isElementDisplayedWithWait(By.xpath(xpath), LOAD_WAIT)){
                element = findElement(By.xpath(xpath));
                element.click();
            }else {
                waitUntilClickable(landscapeSearchBox).sendKeys(demoMailHogUrl);
                androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
                clickIfPresent(acceptAlert, NumericConstants.NUMERIC_12);
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                       By.xpath(xpath)));
                element.click();
            }

            androidDriver.rotate(ScreenOrientation.PORTRAIT);
            waitUntilVisible(subjectText);
            isElementDisplayedWithWait(oneTimeCode);
            pageSource = androidDriver.getPageSource();

        } else {
            IOSDriver iosDriver = ((IOSDriver) driver);
            WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(LOAD_WAIT));
            waitUntilClickable(searchBox).sendKeys(devMailHogUrl + "\n");
            waitUntilClickable(acceptAlert).click();
            iosDriver.rotate(ScreenOrientation.LANDSCAPE);
            xpath = "//*[@label='" + emailAddress + "']";

            if(isElementDisplayedWithWait(acceptAlert, NumericConstants.NUMERIC_12)) {
                acceptAlert.click();
            }
            log.info(LOOKING_FOR_ELEMENT, xpath);

            if(isElementDisplayedWithWait(By.xpath(xpath), LOAD_WAIT)){
                element = findElement(By.xpath(xpath));
                element.click();
            }else {
                waitUntilClickable(landscapeSearchBox).sendKeys(demoMailHogUrl + "\n");
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(xpath)));
                element.click();
            }

            iosDriver.rotate(ScreenOrientation.PORTRAIT);
            waitUntilVisible(subjectText);
            isElementDisplayedWithWait(oneTimeCode);
            pageSource = iosDriver.getPageSource();
        }
        Matcher matcher = OTP_PATTERN.matcher(pageSource);
        if (matcher.find()) {
            log.info("OTP: " + matcher.group(1));
            return matcher.group(1);
        } else {
            Exception exception = new Exception("OTP not found in the Mail Hog");
            TestContext.setLastException(exception);
            throw exception ;
        }
    }
}
