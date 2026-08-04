package com.tsys.testcommon.pageobject.mobile;

import static com.tsys.testcommon.config.Config.LOAD_WAIT;
import static com.tsys.testcommon.config.Config.isAndroid;
import static com.tsys.testcommon.config.Config.isIOS;
import static com.tsys.testcommon.config.Config.isIpad;
import static com.tsys.testcommon.framework.utils.constant.NumericConstants.NUMERIC_1;
import static com.tsys.testcommon.framework.utils.constant.NumericConstants.WAIT_2000;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.util.concurrent.Uninterruptibles;
import com.tsys.testcommon.config.cloudprovider.CloudSpecificAction;
import com.tsys.testcommon.framework.asserts.DippAssertions;
import com.tsys.testcommon.framework.utils.testhelper.TestContext;
import com.tsys.testcommon.model.common.PlatformCoordinates;
import com.tsys.testcommon.model.common.SwipeDirection;
import com.tsys.testcommon.pageobject.web.Page;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobileScreen extends Page {
    private static final String ELEMENT_ON_THE_SCREEN_NOT_FOUND_IN_PAGE_SOURCE = "Element: {} is not found in the source page with wait, tapping coordinates as fallback to reduce test flakiness";
    private static final String CLICK_ELEMENT_IF_FOUND_IN_PAGE_SOURCE = "Element: {} Clicking element if found in the source page";
    private static final String AVAILABLE_CONTEXT = "Available contexts: ";
    private static final CharSequence WEBVIEW = "webview";
    private static final String SWITCHING_TO_CONTEXT = "Switching to context: ";
    private static final String NO_WEBVIEW_CONTEXT_FOUND = "No WebView context found";
    private static final String TAPPING_X = "Tapping coordinates X: ";
    private static final String TAPPING_Y = "Tapping coordinates Y: ";
    private long startTimer;
    private long endTimer;
    private String pageSource;
    public static final String ANDROID_CHROME_PACKAGE = "com.android.chrome";
    public static final String IOS_SAFARI_PACKAGE = "com.apple.mobilesafari";

    protected By loadingCircle = new By.ById("dialogProgressImage");


    @iOSXCUITFindAll({
            @iOSXCUITBy(accessibility = "Done"),
            @iOSXCUITBy(accessibility = "Return"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeButton[@name='Fertig']")
    })
    protected WebElement doneKeyboard;

    @iOSXCUITFindAll({
            @iOSXCUITBy(xpath = "//XCUIElementTypeButton[@name='Go']"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeButton[@name='Fertig']")
    })
    protected WebElement goKeyboard;

    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='drawer-icon' and @visible='true']")
    protected WebElement navigationMenu;

    @AndroidFindBy(xpath = "//androidx.appcompat.widget.LinearLayoutCompat[2]/android.widget.CheckedTextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Profile' and @visible='true']")
    private WebElement profileMenu;

    @AndroidFindBy(id = "toolbarTitle")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")
    protected WebElement toolbarTitleText;

    @AndroidFindBy(xpath = "//androidx.appcompat.widget.LinearLayoutCompat[5]/android.widget.CheckedTextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='More' and @visible='true']")
    private WebElement moreMenu;

    @AndroidFindBy(xpath = "//androidx.appcompat.widget.LinearLayoutCompat[3]/android.widget.CheckedTextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Partners' and @visible='true']")
    private WebElement partners;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Return\"]")
    private WebElement closeKeyboard;

    @AndroidFindBy(id = "wallet_title_text")
    protected WebElement walletTitle;

    @AndroidFindBy(id = "toolbarBack")
    @iOSXCUITFindBy(accessibility = "toolbarBack")
    protected WebElement backButton;

    @AndroidFindBy(id = "placeholder")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeButton")
    protected WebElement backButtonToolbar;

    // At times BS opens English version and at time German
    @AndroidFindBy(xpath = "//*[@text='Sign in' or @text='Anmelden']")
    protected WebElement googlePlaySignInPage;

    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Get' AND type == 'XCUIElementTypeButton'")
    protected WebElement appStoreGetButton;


    @iOSXCUITFindAll({
            @iOSXCUITBy(accessibility = "TabBarItemTitle"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeTextField[@label='Address']"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeTextField[@value='Search or enter website name']"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeTextField[@type='XCUIElementTypeTextField']")
    })
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Search or type URL']")
    WebElement addressField;

    @iOSXCUITFindAll({@iOSXCUITBy(accessibility = "Go"), @iOSXCUITBy(xpath = "//XCUIElementTypeButton[@label='go']")})
    WebElement enterButton;

    @AndroidFindAll({
            @AndroidBy(id = "lottie_loading"),
            @AndroidBy(id = "spinner")
    })
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeImage' AND name == 'Deutschland-App'")
    public WebElement loadingSpinner;

    //Todo add ids for navigation menu and resolve xpath and add iOS ids

    public MobileScreen(WebDriver driver) {
        super(driver);
        try {
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
            waitUntilPageLoads();
        } catch (Exception exception) {
            TestContext.setLastException(exception);
            log.info("Exception while initializing page elements: {}", exception.getMessage());
            throw exception;
        }
    }

    public void waitUntilPageLoads() {
    }

    protected void typeMobile(WebElement textField, String text) {
        try {
            log.info("inputting {} into {}", text, textField.getText().isEmpty() ? textField : textField.getText());
            textField.clear();
            textField.sendKeys(text);
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));// wait for the text to be entered
        } catch (Exception exception) {
            log.info(driver.getPageSource());
            log.info("InvalidElementStateException caught while typing into the text field");
            TestContext.setLastException(exception);
            throw exception;
        }
    }


    public boolean isGooglePlayStoreOpened() {
        return waitUntilVisible(googlePlaySignInPage).isDisplayed();
    }

    public boolean isAppStoreOpened() {
        return waitUntilVisible(appStoreGetButton).isDisplayed();
    }

    protected void mobileScrollUpUntilElementIsOnTheListAndClick(List<WebElement> webElementList, String expectedText) {
        scrollUntilElementIsOnTheListAndClick(webElementList, 0, webElementList.size() - 1, expectedText);
    }

    protected void mobileScrollDownUntilElementIsOnTheListAndClick(List<WebElement> webElementList, String expectedText) {
        scrollUntilElementIsOnTheListAndClick(webElementList, webElementList.size() - 1, 0, expectedText);
    }

    protected void hideIOSKeyboard() {
        if (isIOS()) {
            waitUntilClickable(doneKeyboard).click();
        }
    }

    public void assertElementDisplayWithWait(WebElement element, int waitTimeInSeconds) {
        new DippAssertions().assertThat(isElementDisplayedWithWait(element, waitTimeInSeconds))
                .as(element + " is not displayed")
                .isTrue();

    }


    public boolean isIOSDriver() {
        return driver instanceof IOSDriver;
    }

    public boolean isAndroidDriver() {
        return driver instanceof AndroidDriver;
    }

    private void scrollUntilElementIsOnTheListAndClick(List<WebElement> webElementList, int touchOnElementIndex, int moveToElementIndex, String expectedText) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        while (webElementList.stream().noneMatch(webElement -> webElement.getText().equals(expectedText))) {
            WebElement touchOnElement = webElementList.get(touchOnElementIndex);
            WebElement moveToElement = webElementList.get(moveToElementIndex);
            Sequence sequence = new Sequence(input, 0)
                    .addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), touchOnElement.getLocation().getX(), touchOnElement.getLocation().getY()))
                    .addAction(input.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
                    .addAction(input.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), moveToElement.getLocation().getX(), moveToElement.getLocation().getY()))
                    .addAction(input.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
            ((AppiumDriver) driver).perform(Collections.singletonList(sequence));
        }

        webElementList.stream()
                .filter(webElement -> webElement.getText().equals(expectedText))
                .findFirst()
                .orElseThrow(IllegalStateException::new)
                .click();
    }

    protected void mobileScrollDownUntilElementIsVisibleAndClick(WebElement webElement) {
        mobileScrollDownUntilElementIsVisible(webElement);
        webElement.click();
    }

    protected void mobileScrollUpUntilElementIsVisibleAndClick(WebElement webElement) {
        mobileScrollUpUntilElementIsVisible(webElement);
        webElement.click();
    }

    /**
     * mobileScrollDownUntilElementIsVisible scrolls to the bottom of the Page
     * Assert fail if element isn't found.
     *
     * @param webElement
     */
    protected void mobileScrollDownUntilElementIsVisible(WebElement webElement) {
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 2;
        int toY = dimension.height / 3;

        scrollUntilElementIsVisible(webElement, pointX, fromY, toY);
    }

    protected WebElement mobileScrollDownUntilElementAppears(WebElement webElement) {
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 2;
        int toY = (int) (dimension.height * 0.15);

        log.info(LOOKING_FOR_ELEMENT, webElement);
        scrollUntilElementIsVisible(webElement, pointX, fromY, toY);
        return webElement;
    }

    protected WebElement mobileScrollUpAndDownUntilElementAppears(WebElement webElement) {
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 2;
        int toY = (int) (dimension.height * 0.15);

        log.info(LOOKING_FOR_ELEMENT, webElement);
        scrollUpAndDownUntilElementIsVisible(webElement, pointX, fromY, toY);
        return webElement;
    }

    protected void mobileScrollDownUntilElementIsVisibleAndScrollMore(WebElement webElement) {
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 3 * 2;
        int toY = dimension.height / 3;
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        scrollUntilElementIsVisible(webElement, pointX, fromY, toY);

        Sequence sequence = new Sequence(input, 0)
                .addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), pointX, fromY))
                .addAction(input.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
                .addAction(input.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), pointX, toY))
                .addAction(input.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        ((AppiumDriver) driver).perform(Collections.singletonList(sequence));
    }

    protected void mobileScrollDownOnBottomPartUntilElementIsVisible(WebElement webElement) {
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 4 * 3;
        int toY = dimension.height / 2;

        scrollUntilElementIsVisible(webElement, pointX, fromY, toY);
    }

    protected void mobileScrollUpOnBottomPartUntilElementIsVisible(WebElement webElement) {
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 4 * 3;
        int toY = dimension.height;

        scrollUntilElementIsVisible(webElement, pointX, fromY, toY);
    }

    /**
     * mobileScrollUpUntilElementIsVisible scrolls to the top of the Page
     * Assert fail if element isn't found.
     *
     * @param webElement
     */
    protected void mobileScrollUpUntilElementIsVisible(WebElement webElement) {
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 3;
        int toY = dimension.height / 2;

        scrollUntilElementIsVisible(webElement, pointX, fromY, toY);
    }

    private void scrollUntilElementIsVisible(WebElement webElement, int pointX, int fromY, int toY) {
        int attemptCount = 0;
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        while (!isElementDisplayed(webElement) && attemptCount < 5) {
            Sequence sequence = new Sequence(input, 0)
                    .addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), pointX, fromY))
                    .addAction(input.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
                    .addAction(input.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), pointX, toY))
                    .addAction(input.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
            ((AppiumDriver) driver).perform(Collections.singletonList(sequence));
            attemptCount++;
        }
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
        if (!isElementDisplayed(webElement)) {
            TestContext.setLastException(new NoSuchElementException("Element is not displayed"));
        }
    }

    private void scrollUpAndDownUntilElementIsVisible(WebElement webElement, int pointX, int fromY, int toY) {
        int attemptCount = 0;
        String pageSource = "";
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        while (!isElementDisplayed(webElement) && attemptCount < 7 && !pageSource.equals(driver.getPageSource())) {
            pageSource = driver.getPageSource();
            Sequence sequence = new Sequence(input, 0)
                    .addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), pointX, fromY))
                    .addAction(input.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
                    .addAction(input.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), pointX, toY))
                    .addAction(input.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
            ((AppiumDriver) driver).perform(Collections.singletonList(sequence));
            attemptCount++;
            Uninterruptibles.sleepUninterruptibly(Duration.ofMillis(300));
        }
        Dimension dimension = driver.manage().window().getSize();
        toY = (int) (dimension.height * 0.85);


        attemptCount = 0;
        pageSource = "";
        while (!isElementDisplayed(webElement) && attemptCount < 10 && !pageSource.equals(driver.getPageSource())) {
            pageSource = driver.getPageSource();
            Sequence sequence = new Sequence(input, 0)
                    .addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), pointX, fromY))
                    .addAction(input.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
                    .addAction(input.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), pointX, toY))
                    .addAction(input.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
            ((AppiumDriver) driver).perform(Collections.singletonList(sequence));
            Uninterruptibles.sleepUninterruptibly(Duration.ofMillis(300));
            attemptCount++;
        }
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
        if (!isElementDisplayed(webElement)) {
            TestContext.setLastException(new NoSuchElementException("Element is not displayed"));
        }
    }

    /**
     * Select element by text value on the picker (native drop-down list)
     * Assert fail if element isn't present in the list.
     *
     * @param webElement
     * @param expectedText
     */
    public void selectPickerValue(WebElement webElement, String expectedText) {
        String direction = "Next";
        while (!webElement.getText().equals(expectedText)) {
            try {
                scrollOnPicker(webElement, direction);
            } catch (InvalidElementStateException ex) {
                if (!direction.equals("previous"))
                    direction = "previous";
                else
                    Assert.fail("Element '" + expectedText + "' is not present in the list to select");
            }

        }
        webElement.click();
    }

    /**
     * IOS ONLY
     *
     * @param webElement
     * @param direction  - use "next" for scroll down and "previous" for scroll up
     * @see "https://github.com/appium/appium-xcuitest-driver#mobile-selectpickerwheelvalue-1"
     */
    private void scrollOnPicker(WebElement webElement, String direction) {
        Map<String, Object> args = new HashMap<>();
        args.put("order", direction);
        args.put("offset", 0.15);
        args.put("element", ((RemoteWebElement) webElement).getId());
        ((IOSDriver) driver).executeScript("selectPickerWheelValue", args);
    }

    public void openNavigationMenu() {
        waitUntilClickable(navigationMenu);
        navigationMenu.click();
    }

    public void hideKeyboardIfVisible() {
        try {
            boolean keyboardShown;
            if (isAndroid()) {
                keyboardShown = ((AndroidDriver) driver).isKeyboardShown();
                if (keyboardShown) {
                    ((AndroidDriver) driver).hideKeyboard();
                }
            } else {
                keyboardShown = ((IOSDriver) driver).isKeyboardShown();
                if (keyboardShown) {
                    ((IOSDriver) driver).hideKeyboard();
                }
            }
        } catch (WebDriverException e) {
            log.info("Keyboard not present or couldn't be hidden – safely ignore or log: " + e.getMessage());
        }
    }


    public String getAppPackage(WebDriver driver) {
        String appPackage = "";
        if (isAndroidDriver()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            appPackage = androidDriver.getCurrentPackage();
        }

        return appPackage;
    }

    public String getAppActivity(WebDriver driver) {
        String activity = "";
        if (isAndroidDriver()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            activity = androidDriver.currentActivity();
        }

        return activity;
    }

    /**
     * Get Mechanism used to locate Web Elements
     */
    public By getByFromElement(WebElement element) {
        By by;
        // Located by By.chained({By.id: fullAddressValue}) ->  By.id: fullAddressValue
        String[] pathVariables = element.toString().replaceAll(".*\\{(.*)}.*", "$1").split(": ");
        String selector = pathVariables[0].trim();
        String value = pathVariables[1].trim();

        switch (selector) {
            case "By.id":
            case "By.AccessibilityId":
                by = By.id(value);
                break;
            case "AppiumBy.accessibilityId":
                by = AppiumBy.accessibilityId(value);
                break;
            case "AppiumBy.id":
                by = AppiumBy.id(value);
                break;
            case "By.xpath":
                by = By.xpath(value);
                break;
            case "By.name":
                by = By.name(value);
                break;
            case "By.cssSelector":
                by = By.cssSelector(value);
                break;
            case "By.className":
                by = By.className(value);
                break;
            case "By.tagName":
                by = By.tagName(value);
                break;
            default:
                throw new IllegalStateException("Locator : " + selector + " not found");
        }
        return by;
    }

    /**
     * Touch action to the center of the screen
     * Used as solution to remove focus from element
     */
    public void touchToRemoveFocus() {
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 2;
        //TODO:
    }

    public void tapAtCoordinates(Point point) {

        log.info(TAPPING_X + point.getX());
        log.info(TAPPING_Y + point.getY());
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(input, 1)
                .addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), point.getX(), point.getY()))
                .addAction(input.createPointerDown(0))
                .addAction(input.createPointerUp(0));
        ((AppiumDriver) driver).perform(Collections.singletonList(tap));

    }

    public void swipe(SwipeDirection swipeDirection, Duration duration) {
        CloudSpecificAction.getInstance(driver).swipe(swipeDirection, duration);
    }

    public void clickAboveKeyboard() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Object> tap = new HashMap<>();
        tap.put("x", 100); // X coordinate on safe area
        tap.put("y", 100); // Y coordinate above keyboard
        js.executeScript("tap", tap);
    }

    public void mobileScrollDownToBottom() {
        startTimer = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTimer < WAIT_2000) {
            swipe(SwipeDirection.UP, Duration.ofSeconds(1));
        }
    }

    public void mobileScrollDownWithTimes(int milliSeconds) {
        startTimer = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTimer < (long) milliSeconds) {
            swipe(SwipeDirection.UP, Duration.ofMillis(1));
        }
    }

    public void mobileScrollUpWithTimes(int milliSeconds) {
        startTimer = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTimer < (long) milliSeconds) {
            swipe(SwipeDirection.DOWN, Duration.ofSeconds(1));
        }
    }

    public void mobileScrollToBottomAndSearchForElementText(String elementName) {
        startTimer = System.currentTimeMillis();
        while (elementNotPresent(elementName) && endTimer - startTimer < 4000) {
            swipe(SwipeDirection.UP, Duration.ofSeconds(1));
            endTimer = System.currentTimeMillis();
            pageSource = null;
        }
    }

    public void mobileScrollToBottomUntilElementNotVisible(WebElement element) {
        startTimer = System.currentTimeMillis();
        while (!isElementDisplayed(element) && endTimer - startTimer < 900) {
            swipe(SwipeDirection.UP, Duration.ofSeconds(1));
            endTimer = System.currentTimeMillis();
        }
    }

    public void scrollPageDownUsingTouch() {
        Dimension dimension = driver.manage().window().getSize();

        int startX = dimension.width / 2;
        int startY = (int) (dimension.height * 0.8);
        int endY = (int) (dimension.height * 0.2);
        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }


    public void mobileScrollToTopAndSearchForElementText(String elementName) {
        startTimer = System.currentTimeMillis();
        while (elementNotPresent(elementName) && endTimer - startTimer < 4000) {
            swipe(SwipeDirection.DOWN, Duration.ofSeconds(1));
            endTimer = System.currentTimeMillis();
            pageSource = null;
        }
    }

    public void mobileScrollUpToTop() {
        startTimer = System.currentTimeMillis();
        while (endTimer - startTimer < 4000) {
            swipe(SwipeDirection.DOWN, Duration.ofSeconds(1));
            endTimer = System.currentTimeMillis();
        }
    }

    public boolean elementNotPresent(String elementName) {
        pageSource = driver.getPageSource();
        return !pageSource.contains(elementName);
    }

    public void closeIOSKeyboard() {
        if (isIOS()) {
            waitUntilClickable(closeKeyboard).click();
        }
    }

    public void waitUntilMobileLoadingScreenNotVisible() {
        waitUntilNotVisible(loadingCircle);
    }


    @SneakyThrows
    private void clickUrlAddressFiledAndSendKeys(String address) {
        waitUntilClickable(addressField).click();
        addressField.sendKeys(address);
    }

    private void clickEnter() {
        waitUntilClickable(enterButton).click();
    }


    public <T> T sendKeyToAddressField(Class<T> clazz, String address) {
        openMobileBrowser();
        if (isIOS()) {
            clickUrlAddressFiledAndSendKeys(address);
            clickEnter();
        } else {
            clickUrlAddressFiledAndSendKeys(address);
            new Actions(driver)
                    .sendKeys(Keys.ENTER)
                    .perform();
        }
        return newPageInstance(clazz);
    }

    public void openMobileBrowser() {
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

    protected void clickIfPresentOtherwiseTapCoordinates(WebElement element, Point androidCoordinates, Point iOSCoordinates, Point iPadCoordinates) {

        log.info(CLICK_ELEMENT_IF_FOUND_IN_PAGE_SOURCE, element);
        if (isElementDisplayedWithWait(element, 30)) {
            element.click();
        } else {

            log.info(ELEMENT_ON_THE_SCREEN_NOT_FOUND_IN_PAGE_SOURCE, element);
            if (isAndroid()) {
                tapAtCoordinates(androidCoordinates);
            } else if (isIOS()) {
                tapAtCoordinates(iOSCoordinates);
            } else if (isIpad()) {
                tapAtCoordinates(iPadCoordinates);
            }
        }
    }

    protected void clickIfPresentOtherwiseTapCoordinates(WebElement element, PlatformCoordinates platformCoordinates) {

        log.info(CLICK_ELEMENT_IF_FOUND_IN_PAGE_SOURCE, element);
        if (isElementDisplayedWithWait(element, 30)) {
            element.click();
        } else {

            log.info(ELEMENT_ON_THE_SCREEN_NOT_FOUND_IN_PAGE_SOURCE, element);
            if (isAndroid()) {
                tapAtCoordinates(platformCoordinates.getAndroidCoordinates());
            } else if (isIOS()) {
                tapAtCoordinates(platformCoordinates.getIOSCoordinates());
            } else if (isIpad()) {
                tapAtCoordinates(platformCoordinates.getIPadCoordinates());
            }
        }
    }

    protected void clickIfPresentOtherwiseTapCoordinates(WebElement element, PlatformCoordinates platformCoordinates, int waitTime) {

        log.info(CLICK_ELEMENT_IF_FOUND_IN_PAGE_SOURCE, element);
        if (isElementDisplayedWithWait(element, waitTime)) {
            element.click();
        } else {

            log.info(ELEMENT_ON_THE_SCREEN_NOT_FOUND_IN_PAGE_SOURCE, element);
            log.info("{} Print Source Page for debugging", driver.getPageSource());
            if (isAndroid()) {
                tapAtCoordinates(platformCoordinates.getAndroidCoordinates());
            } else if (isIOS()) {
                tapAtCoordinates(platformCoordinates.getIOSCoordinates());
            } else if (isIpad()) {
                tapAtCoordinates(platformCoordinates.getIPadCoordinates());
            }
        }
    }

    /**
     * Finds an element that contains the provided text in its visible label or name,
     * This method is an extreme measure and should only be used when (lazy) dev can not provide unique element locators
     *
     * @param text the text to search for (e.g., part of an email address or any other partially constant string)
     * @return the found MobileElement
     */
    public WebElement findMobileElementByText(String text) {
        if (driver instanceof AndroidDriver) {
            // Android – use XPath with contains on the text attribute
            String xpathLocator = "//*[contains(@text, '" + text + "')]";
            return driver.findElement(AppiumBy.xpath(xpathLocator));
        } else if (driver instanceof IOSDriver) {
            // iOS – use an iOS predicate string with CONTAINS on the name attribute
            String predicate = "name CONTAINS '" + text + "'";
            return driver.findElement(AppiumBy.iOSNsPredicateString(predicate));
        } else {
            throw new UnsupportedOperationException("Unsupported driver platform");
        }
    }

    public boolean isTextDisplayedWithWait(String text, int waitTimeInSeconds) {
        WebElement element = findNullableMobileElementByTextWithWait(text, waitTimeInSeconds);
        return element != null;
    }

    public WebElement findNullableMobileElementByTextWithWait(String text, int waitTimeInSeconds) {
        WebElement element = null;
        for (int elapsed = 0; elapsed < waitTimeInSeconds; elapsed++) {
            try {
                if (isAndroid()) {
                    element = findElement(By.xpath("//*[contains(@text,'" + text + "')]"));
                    if (element.isDisplayed()) {
                        return element;
                    }
                } else {
                    element = findElement(By.xpath("//*[contains(@value,'" + text + "')]"));
                    if (element.isDisplayed()) {
                        return element;
                    }
                }
            } catch (NoSuchElementException | StaleElementReferenceException ignored) {
                log.info("Element not found after: " + elapsed);
            }
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        }
        return element;
    }

    public void switchToWebView() {
        try {
            if (isAndroid()) {
                AndroidDriver androidDriver = ((AndroidDriver) driver);

                log.info(AVAILABLE_CONTEXT + androidDriver.getContextHandles());
                for (String contextName : androidDriver.getContextHandles()) {
                    if (contextName.toLowerCase().contains(WEBVIEW)) {

                        log.info(SWITCHING_TO_CONTEXT + contextName);
                        androidDriver.context(contextName);
                        return;
                    }
                }
                throw new RuntimeException(NO_WEBVIEW_CONTEXT_FOUND);
            } else {
                IOSDriver iosDriver = ((IOSDriver) driver);

                log.info(AVAILABLE_CONTEXT + iosDriver.getContextHandles());
                for (String contextName : iosDriver.getContextHandles()) {
                    if (contextName.toLowerCase().contains(WEBVIEW)) {

                        log.info(SWITCHING_TO_CONTEXT + contextName);
                        iosDriver.context(contextName);
                        return;
                    }
                }
                throw new RuntimeException(NO_WEBVIEW_CONTEXT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to switch to WebView context: " + e.getMessage(), e);
        }
    }

    public void switchToNative() {
        try {

            log.info("Switched to native context.");
            if (isAndroid()) {
                AndroidDriver androidDriver = ((AndroidDriver) driver);
                androidDriver.context(NATIVE_APP);
            } else {
                IOSDriver iosDriver = ((IOSDriver) driver);
                iosDriver.context(NATIVE_APP);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not switch to native context: " + e.getMessage(), e);
        }
    }

    private void swipeUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        if (driver instanceof IOSDriver) {
            // iOS → old-style "mobile: swipe"
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "up");
            js.executeScript("swipe", params);

        } else if (driver instanceof AndroidDriver) {
            // Android → new "mobile: swipeGesture" and needs area rect
            Dimension size = driver.manage().window().getSize();
            int left = 0;
            int top = 0;
            int width = size.getWidth();
            int height = size.getHeight();

            Map<String, Object> params = new HashMap<>();
            params.put("direction", "up");
            params.put("percent", 0.75);  // swipe strength
            params.put("left", left);
            params.put("top", top);
            params.put("width", width);
            params.put("height", height);

            js.executeScript("swipeGesture", params);

        } else {
            throw new IllegalStateException("Unsupported driver type for swipeUp: " + driver.getClass());
        }
    }

    public void scrollUntilVisible(WebElement element) {
        int maxSwipes = 7;
        for (int i = 0; i < maxSwipes; i++) {
            try {
                if (element.isDisplayed()) return;
            } catch (Exception ignored) {
                log.info("Element not displayed yet, swiping up... Attempt: {}", i + 1);
            }
            swipeUp();
        }
        throw new RuntimeException("Element not found after scrolling: " + element);
    }

    public static boolean isElementPresentWithMultipleLocator(int waitTimeInSeconds, WebElement... elements) {
        int initialWaitTime = waitTimeInSeconds;
        while (waitTimeInSeconds > 0) {
            for (WebElement element : elements) {
                if (isElementPresent(element)) {
                    return true;
                }
            }
            waitTimeInSeconds--;
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
            printElapsedTime(initialWaitTime, waitTimeInSeconds);
        }
        log.info(Arrays.toString(elements) + ELEMENT_NOT_FOUND + initialWaitTime + WAIT_SECONDS);
        return false;
    }


    public static WebElement getMobileElementViaMultipleLocators(int waitTimeInSeconds, WebElement... elements) {
        int initialWaitTime = waitTimeInSeconds;
        while (waitTimeInSeconds > 0) {
            for (WebElement element : elements) {
                if (isElementPresent(element)) {
                    log.info("{} is visible ", element.getText().isEmpty() ? element : element.getText());
                    return element;
                }
            }
            waitTimeInSeconds--;
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
            printElapsedTime(initialWaitTime, waitTimeInSeconds);
        }
        log.info(ELEMENT_NOT_FOUND_WITH_MULTIPLE_LOCATORS + Arrays.toString(elements));
        throw new NoSuchElementException(Arrays.toString(elements) + ELEMENT_NOT_FOUND + initialWaitTime + WAIT_SECONDS);
    }


    public WebElement mobileScrollAndBringElementToTheMiddle(WebElement element) {

        if (element == null) {
            throw new IllegalArgumentException("Element must not be null.");
        }
        log.info(LOOKING_FOR_ELEMENT, element);
        Dimension screen;
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            screen = androidDriver.manage().window().getSize();
        } else if (isIOS()) {
            IOSDriver iosDriver = ((IOSDriver) driver);
            screen = iosDriver.manage().window().getSize();
        } else {
            throw new IllegalStateException("Driver must be AndroidDriver or IOSDriver.");
        }


        int screenWidth = screen.getWidth();
        int screenHeight = screen.getHeight();

        int middleY = screenHeight / 2;
        int tolerance = 80;

        Instant endTime = Instant.now().plus(Duration.ofSeconds(LOAD_WAIT));

        boolean scrollDown = true;
        int swipeCount = 0;

        while (Instant.now().isBefore(endTime)) {
            if (isElementDisplayedWithWait(element, NUMERIC_1)) {
                moveElementToMiddle(element, middleY, tolerance);
                return element;
            } else {
                if (scrollDown) {
                    swipe(screenWidth / 2, (int) (screenHeight * 0.80), screenWidth / 2, (int) (screenHeight * 0.30));
                } else {
                    swipe(screenWidth / 2, (int) (screenHeight * 0.30), screenWidth / 2, (int) (screenHeight * 0.80));
                }
            }
            swipeCount++;

            // Change direction every 4 swipes
            if (swipeCount >= 4) {
                swipeCount = 0;
                scrollDown = !scrollDown;
            }
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
        }
        TestContext.setLastException(new NoSuchElementException("Element not found after waiting for element " + element + " " + Duration.ofSeconds(LOAD_WAIT).getSeconds()
                + " seconds."));
        throw new TimeoutException(
                "Unable to locate element after scrolling for "
                        + Duration.ofSeconds(LOAD_WAIT).getSeconds()
                        + " seconds.");

    }


    private void moveElementToMiddle(WebElement element, int targetY, int tolerance) {

        Dimension screen;
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            screen = androidDriver.manage().window().getSize();
        } else if (isIOS()) {
            IOSDriver iosDriver = ((IOSDriver) driver);
            screen = iosDriver.manage().window().getSize();
        } else {
            throw new IllegalStateException("Driver must be AndroidDriver or IOSDriver.");
        }

        int screenWidth = screen.getWidth();
        int screenHeight = screen.getHeight();

        int maxAdjustments = 3;

        while (maxAdjustments-- > 0) {

            try {

                Point point = element.getLocation();

                int centerY = point.getY() + element.getSize().getHeight() / 2;

                int delta = centerY - targetY;

                if (Math.abs(delta) <= tolerance) {
                    return;
                }

                if (delta > 0) {

                    swipe(screenWidth / 2, (int) (screenHeight * 0.75), screenWidth / 2, (int) (screenHeight * 0.45));

                } else {

                    swipe(screenWidth / 2, (int) (screenHeight * 0.45), screenWidth / 2, (int) (screenHeight * 0.75));
                }

                Thread.sleep(250);

            } catch (StaleElementReferenceException ignored) {
                break;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void swipe(int startX, int startY, int endX, int endY) {

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        startX,
                        startY));

        swipe.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()));

        swipe.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(600),
                        PointerInput.Origin.viewport(),
                        endX,
                        endY));

        swipe.addAction(
                finger.createPointerUp(
                        PointerInput.MouseButton.LEFT.asArg()));

        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            androidDriver.perform(Collections.singletonList(swipe));
        } else if (isIOS()) {
            IOSDriver iosDriver = ((IOSDriver) driver);
            iosDriver.perform(Collections.singletonList(swipe));
        } else {
            throw new IllegalStateException("Driver must be AndroidDriver or IOSDriver.");
        }


    }

    public boolean isElementPresentWhileScrollingUpAndDown(WebElement element) {
        log.info(LOOKING_FOR_ELEMENT, element);
        Dimension dimension = driver.manage().window().getSize();
        int pointX = dimension.width / 2;
        int fromY = dimension.height / 2;
        int toY = (int) (dimension.height * 0.15);


        String pageSource = "";
        int counter = 0;
        while (!isElementPresent(element) && counter < 4 && !pageSource.equals(driver.getPageSource())) {
            pageSource = driver.getPageSource();
            counter++;
            log.info(LOOKING_FOR_ELEMENT, element);
            scrollUpAndDownUntilElementIsVisible(element, pointX, fromY, toY);
            Uninterruptibles.sleepUninterruptibly(Duration.ofMillis(300));
            if (isElementPresent(element)) {
                return element.isDisplayed();
            }
        }
        pageSource = "";
        counter = 0;
        toY = (int) (dimension.height * 0.85);
        while (!isElementPresent(element) && counter < 7 && !pageSource.equals(driver.getPageSource())) {
            pageSource = driver.getPageSource();
            counter++;
            scrollUpAndDownUntilElementIsVisible(element, pointX, fromY, toY);
            Uninterruptibles.sleepUninterruptibly(Duration.ofMillis(300));
            if (isElementPresent(element)) {
                return element.isDisplayed();
            }

        }
        return false;
    }

}