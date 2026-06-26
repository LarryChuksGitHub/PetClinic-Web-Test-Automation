package com.verimi.testcommon.pageobject.web;

import static com.verimi.testcommon.config.Config.LOAD_WAIT;
import static com.verimi.testcommon.framework.utils.constant.CommonConstants.CLASS_ATTRIBUTE;
import static com.verimi.testcommon.framework.utils.constant.CommonConstants.VALUE_ATTRIBUTE;
import static com.verimi.testcommon.framework.utils.constant.NumericConstants.NUMERIC_1;
import static com.verimi.testcommon.framework.utils.constant.NumericConstants.NUMERIC_20;
import static com.verimi.testcommon.framework.utils.constant.NumericConstants.NUMERIC_4;
import static com.verimi.testcommon.framework.utils.constant.NumericConstants.WAIT_500;
import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.util.concurrent.Uninterruptibles;
import com.verimi.testcommon.config.Config;
import com.verimi.testcommon.config.cloudprovider.CloudProvider;
import com.verimi.testcommon.framework.utils.BrowserTabOperation;
import com.verimi.testcommon.framework.utils.retry.RetryUtils;
import com.verimi.testcommon.framework.utils.sleep.SleepUtil;
import com.verimi.testcommon.framework.utils.testhelper.TestContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public abstract class Page {

    public static final String HEADER_LOGOUT_ID = "header-logout";
    public static final String DE_HEADER_TITLE = "Bei Verimi anmelden";
    public static final String NO_PASSWORD_TEXT_DE = "Kein Passwort oder vergessen?";
    public static final String TENANT_LOCATOR = "tenant";
    protected static final String MY_DATA_MENU = "menu-profile";
    private static final String SAVE_BUTTON_ID = "save";
    private static final String OK_BUTTON = "ok";
    private static final String LOADING = "img[class='loading']";
    private static final String MODAL_CONTENT = "div.modal-content";
    private static final By MODAL_CONTENT_BY = By.cssSelector(MODAL_CONTENT);
    public static final String WEBVIEW_CHROME = "WEBVIEW_chrome";
    public static final String NATIVE_APP = "NATIVE_APP";
    public static final String CONTENT_DESC_ATTRIBUTE = "content-desc";
    public static final String ELEMENT_DISPLAY_INFO = "Element {} is displayed: {}";
    public static final String LOOKING_FOR_ELEMENT = "Looking for: {}";
    public static final String ELEMENT_NOT_FOUND_WITH_MULTIPLE_LOCATORS = "Element not found using any of the locators: ";
    public static final String ELEMENT_NOT_FOUND = " Element not found after waiting for ";
    public static final String WAIT_SECONDS = " seconds...";
    private final String url;
    protected WebElement save;
    protected WebDriver driver;

    @FindBy(id = SAVE_BUTTON_ID)
    protected By saveBy = By.id(SAVE_BUTTON_ID);

    @FindBy(id = OK_BUTTON)
    protected WebElement okButton;
    protected By okButtonBy = By.id(OK_BUTTON);

    @FindBy(css = "body")
    protected WebElement body;

    @FindBy(id = "langEn")
    protected WebElement changeLanguageToEnLink;

    @FindBy(id = "langDe")
    protected WebElement changeLanguageToDeLink;
    @FindBy(id = "confirmModal")
    protected WebElement confirmModal;
    @FindBy(id = "cancelModal")
    protected WebElement cancelModal;
    @FindBy(id = MY_DATA_MENU)
    protected WebElement myProfileMenu;
    @FindBy(id = "cancel")
    protected WebElement cancel;
    @FindBy(id = "header-logo")
    private WebElement homePage;
    @FindBy(id = "menu-esign")
    private WebElement mySignatureMenu;
    @FindBy(id = "menu-verimis")
    private WebElement myVerimisMenu;
    @FindBy(id = "menu-activities")
    private WebElement activitiesMenu;

    @FindBy(id = "menu-settings")
    private WebElement settingsMenu;

    @FindBy(css = MODAL_CONTENT)
    private List<WebElement> modalContentOpen;

    @FindBy(id = HEADER_LOGOUT_ID)
    private WebElement logOutButton;

    @FindBy(css = "[data-i18n='portal:logout']")
    private WebElement logOutNewButton;

    @FindBy(css = "[data-i18n='portal:deleteAccountModal.subtitle']")
    public WebElement deleteAccountModal;


    private final By loading = By.cssSelector(LOADING);

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = SAVE_BUTTON_ID)
    protected WebElement saveButton;
    @FindBy(className = "logo")
    private WebElement logo;

    @FindBy(id = "cancelButton")
    private WebElement cancelButton;

    @FindBy(css = "input[placeholder *= 'beispiel@mail.de']")
    private WebElement germanEmailAddressPlaceHolder;

    @FindBy(xpath = " //*[text() = '" + DE_HEADER_TITLE + "']")
    private WebElement germanPageHeader;

    @FindBy(xpath = " //*[text() = '" + NO_PASSWORD_TEXT_DE + "']")
    private WebElement noPasswordDe;

    @FindBy(css = "#langDe[aria-pressed='true']")
    private WebElement activeGermanLanguageButton;

    //for OTL there are 2 logos
    @FindBy(css = "#spLogoImage,.transferHeaderRight")
    private List<WebElement> partnerLogos;

    @FindAll({
            @FindBy(xpath = "//*[text()='Weiter']"),
            @FindBy(id = "continue"),
    })
    private WebElement nextContinue;

    protected Page(WebDriver driver, String path) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        url = Config.getFeUrl() + path;
    }

    protected Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        url = Config.getFeUrl();
    }

    public static void waitForMilliSecs(int milliSecs) {
        SleepUtil.sleep(milliSecs);
    }


    public static WebElement getWebElementViaMultipleLocators(WebDriver driver, int waitTimeInSeconds, List<By> locators) {
        int initialWaitTime = waitTimeInSeconds;
        while (waitTimeInSeconds > 0) {
            for (By locator : locators) {
                List<WebElement> elements = (driver.findElements(locator));
                if (!elements.isEmpty()) {
                    log.info(elements.get(0).toString(), "found using locator: " + locator);
                    return elements.get(0); // return first match
                }
            }
            waitTimeInSeconds--;
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
            printElapsedTime(initialWaitTime, waitTimeInSeconds);
        }
        log.info(ELEMENT_NOT_FOUND_WITH_MULTIPLE_LOCATORS + locators.toString());
        log.info(driver.getPageSource());
        TestContext.setLastException(new NoSuchElementException(locators + ELEMENT_NOT_FOUND));
        throw new NoSuchElementException(locators + ELEMENT_NOT_FOUND + initialWaitTime + WAIT_SECONDS);
    }

    public static WebElement getWebElementViaMultipleLocators(WebDriver driver, int waitTimeInSeconds, By... locators) {
        int initialWaitTime = waitTimeInSeconds;
        while (waitTimeInSeconds > 0) {
            for (By locator : locators) {
                List<WebElement> elements = (driver.findElements(locator));
                if (!elements.isEmpty()) {
                    log.info(elements.get(0).toString(), "found using locator: " + locator);
                    return elements.get(0); // return first match
                }
            }
            waitTimeInSeconds--;
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
            printElapsedTime(initialWaitTime, waitTimeInSeconds);
        }
        log.info(ELEMENT_NOT_FOUND_WITH_MULTIPLE_LOCATORS + Arrays.toString(locators));
        log.info(driver.getPageSource());
        TestContext.setLastException(new NoSuchElementException(locators + ELEMENT_NOT_FOUND));
        throw new NoSuchElementException(Arrays.toString(locators) + ELEMENT_NOT_FOUND + initialWaitTime + WAIT_SECONDS);
    }

    public static boolean isElementPresentWithMultipleLocator(WebDriver driver, int waitTimeInSeconds, List<By> locators) {
        int initialWaitTime = waitTimeInSeconds;
        while (waitTimeInSeconds > 0) {
            for (By locator : locators) {
                List<WebElement> elements = (driver.findElements(locator));
                if (!elements.isEmpty()) {
                    log.info(elements.get(0).toString(), "found using locator: " + locator);
                    return true;
                }
            }
            waitTimeInSeconds--;
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NUMERIC_1));
            printElapsedTime(initialWaitTime, waitTimeInSeconds);
        }
        log.info((locators + ELEMENT_NOT_FOUND + initialWaitTime + WAIT_SECONDS));
        log.info(driver.getPageSource());
        return false;
    }

    public static void printElapsedTime(int initialWaitTime, int waitTimeInSeconds) {
        if (waitTimeInSeconds % 4 == 0) {
            log.info("Waited for " + (initialWaitTime - waitTimeInSeconds) + WAIT_SECONDS);
        }
    }

    public static boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }


    public void clickOnCancelButtonAndTryToWaitUntilUrlContainsRedirectUri(String path) throws SQLException {
        scrollToElement(cancelButton);
        waitUntilVisible(cancelButton).click();
        waitUntilUrlContains(path, 10, 300);
    }

    public void clickOnCancelButton() {
        waitUntilVisible(cancelButton).click();
    }

    public void clickOnVerimiLogo() {
        waitUntilClickable(homePage).click();
    }


    /**
     * @param el Use js to click element only when indispensable. Users do not click using js
     */
    public void clickElementUsingJavaScript(WebElement el) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", el);
    }

    private void waitUntilMyDataMenuVisible() {
        waitUntilVisible(myProfileMenu);
    }


    public void clickIfPresent(WebElement element, String elementName) {
        if (driver.getPageSource().contains(elementName)) {
            waitUntilClickable(element).click();
        }
    }


    public boolean isCancelButtonVisible() {
        return isElementDisplayedWithWait(cancelButton, 5);
    }


    public void waitUntilElementIsDisabled(WebElement element) {
        try {
            await().atMost(30, SECONDS)
                    .pollInterval(1, SECONDS)
                    .until(() -> !waitUntilVisible(element).isEnabled());
        } catch (ConditionTimeoutException e) {
            log.info(driver.getPageSource());
            log.error("Timed out waiting for element to be disabled");
        }
    }


    protected WebElement waitUntilPresent(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public Page go() throws SQLException {
        driver.get(url);
        waitUntilPageLoads();
        return this;
    }

    public void go(String url) {
        driver.get(url);
        waitForPageReadyState();
    }

    public void refreshPage() {
        driver.navigate().refresh();
        waitForPageReadyState();
    }

    public void navigateBack() {
        driver.navigate().back();
        waitForPageReadyState();
    }

    protected void refreshPageAndScrollToElement(WebElement element) {
        driver.navigate().refresh();
        scrollToElement(element);
    }

    public void switchToGivenLang(String language) {
        if (Objects.equals(language, "de"))
            changeLanguageToDeLink.click();
        else if (Objects.equals(language, "en"))
            changeLanguageToEnLink.click();
        waitForMilliSecs(300);
    }

    public void switchToGermanLocale() {
        if (isElementDisplayedWithWait(changeLanguageToDeLink, NUMERIC_20)) {
            waitUntilVisible(changeLanguageToDeLink).click();

            int attempts = 0;
            while (!driver.getTitle().contains(DE_HEADER_TITLE) && attempts < 1) {

                log.info("Retrying to switch to German locale: attempt {}", attempts + 1);
                waitUntilVisible(changeLanguageToDeLink).click();
                Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
                attempts++;
            }
        }

    }

    public void changeLanguageToGerman() {
        waitUntilVisible(changeLanguageToDeLink).click();
        try {
            waitUntilVisible(activeGermanLanguageButton);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to switch to German locale", e);
        }
    }

    protected void selectDropDownValueByText(WebElement element, String value) {
        if (!value.isEmpty()) {
            waitUntilVisible(element);
            Select dropDown = new Select(element);
            dropDown.selectByVisibleText(value);
        }
    }

    protected void selectDropDownValueByValue(WebElement element, String value) {
        if (!value.isEmpty()) {
            waitUntilVisible(element);
            Select dropDown = new Select(element);
            dropDown.selectByValue(value);
        }
    }

    protected void selectFirstDropDownValue(WebElement element) {
        waitUntilVisible(element);
        Select dropDown = new Select(element);
        dropDown.selectByIndex(1);
    }

    public boolean isLogoutButtonDisplayed() {
        return isElementDisplayedWithWait(logOutButton);
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected boolean isElementDisplayedWithWait(WebElement element) {
        try {
            waitUntilVisible(element, LOAD_WAIT);
            return element.isDisplayed();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException exception) {
            return false;
        }
    }

    protected boolean isElementDisplayedWithWait(By element) {
        try {
            waitUntilVisible(element, LOAD_WAIT);
            return !driver.findElements(element).isEmpty();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException exception) {
            return false;
        }
    }

    protected boolean isElementDisplayedWithWait(WebElement element, int waitSec) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitSec))
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    protected boolean isElementDisplayedWithWait(By by, int waitSec) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitSec))
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            return isElementDisplayed(by);
        } catch (Exception exception) {
            return false;
        }
    }

    protected boolean isElementPresent(By element) {
        return !driver.findElements(element).isEmpty();
    }

    protected boolean isElementPresentWithWait(By by) {
        return isElementPresentWithWait(by, LOAD_WAIT);
    }

    protected boolean isElementPresentWithWait(By by, int waitSec) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitSec))
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (NoSuchElementException | TimeoutException exception) {
            return false;
        }
    }

    protected boolean isElementDisplayed(By by) {
        try {
            WebElement element = driver.findElement(by);

            log.info(LOOKING_FOR_ELEMENT, element);
            log.info("{} is visible ",
                    element.getText().isEmpty() ? element : element.getText());
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public abstract void waitUntilPageLoads() throws SQLException;

    protected void waitForPageReadyState() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
    }

    protected WebElement waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT));
        try {
            log.info(LOOKING_FOR_ELEMENT, element);
            wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
            wait.ignoring(StaleElementReferenceException.class)
                    .pollingEvery(Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOf(element));
            return element;
        } catch (NoSuchElementException | TimeoutException exception) {
            TestContext.setLastException(exception);
            log.info(driver.getPageSource());
            throw new TimeoutException("Element not found after waiting for " + LOAD_WAIT + " seconds: " + element, exception);
        }
    }

    protected final List<WebElement> waitUntilVisible(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT));
        try {
        log.info(LOOKING_FOR_ELEMENT, elements);
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
        }
        wait.ignoring(StaleElementReferenceException.class)
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfAllElements(elements));

        log.info("Elements found are: {}", elements.stream().map(WebElement::isDisplayed).toList());
        return elements;
        } catch (NoSuchElementException | TimeoutException exception) {
            TestContext.setLastException(exception);
            log.info(driver.getPageSource());
            throw new TimeoutException("Element not found after waiting for " + LOAD_WAIT + " seconds: " + exception);
        }
    }


    @SneakyThrows
    public void waitUntilUrlNotContains(String path) {
        RetryUtils.tryToWaitUntilConditionFulfilled(() -> !Objects.requireNonNull(driver.getCurrentUrl(),
                        "driver.getCurrentUrl() returned null").contains(path),
                60,
                950,
                "Waiting for URL not to contain '" + path + "'. Current URL is: " + driver.getCurrentUrl());
    }

    public void waitUntilUrlContains(String path) throws SQLException {
        RetryUtils.tryToWaitUntilConditionFulfilled(() -> driver.getCurrentUrl().contains(path),
                60,
                500,
                "Waiting for URL to contain '" + path + "'. Current URL is: " + driver.getCurrentUrl());
    }

    protected void waitUntilUrlContains(String path, int numberOfRetries, int pollingInMilliSecs) throws SQLException {
        RetryUtils.tryToWaitUntilConditionFulfilled(() -> driver.getCurrentUrl().contains(path),
                numberOfRetries,
                pollingInMilliSecs,
                "Waiting for URL to contain '" + path + "'.");
    }

    public void clear(WebElement webElement) {
        webElement.clear();
    }

    protected void type(WebElement input, String text) {
        waitUntilVisible(input);
        deleteFieldContent(input);
        input.sendKeys(text);
    }

    /**
     * Handles StaleElementReferenceException resulting from dom changes
     *
     * @param locator
     * @param text
     */
    protected void safeType(By locator, String text) {
        try {
            WebElement element = findElement(locator);
            type(element, text);
        } catch (StaleElementReferenceException ex) {
            WebElement element = findElement(locator);
            type(element, text);
        }
    }


    /**
     * emulate typing slowly i.e. street filed which invoke auto suggestion
     *
     * @param input
     * @param text
     */
    public void typeSlowly(WebElement input, String text) {
        waitUntilVisible(input).click();
        deleteFieldContent(input);
        text.chars().mapToObj(i -> valueOf((char) i)).forEachOrdered(i ->
                {
                    input.sendKeys(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    /**
     * Using sendKeys on some fields i.e. email truncates test data
     * Enters data char by char to overcome https://github.com/angular/protractor/issues/3196#issuecomment-227788976
     *
     * @param input
     * @param text
     */
    public void typeCharByChar(WebElement input, String text) {
        waitUntilVisible(input).click();
        deleteFieldContent(input);
        text.chars().mapToObj(i -> valueOf((char) i)).forEachOrdered(i ->
                input.sendKeys(i)
        );
    }

    protected void deleteFieldContent(WebElement input) {
        waitUntilVisible(input).clear();
    }

    protected Boolean waitUntilAttributeToBe(WebElement element, String attribute, String... values) {
        List<ExpectedCondition<Boolean>> conditions = new ArrayList();
        Arrays.stream(values)
                .distinct()
                .forEach(value -> conditions.add(ExpectedConditions.attributeToBe(element, attribute, value)));
        return new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT))
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.or(conditions.toArray(new ExpectedCondition<?>[conditions.size()])));
    }

    public WebElement waitUntilVisible(WebElement element, int waitSec) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitSec));
        try {
            log.info(LOOKING_FOR_ELEMENT, element);
            webDriverWait.pollingEvery(Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOf(element));
            return element;
        } catch (NoSuchElementException | TimeoutException exception) {
            TestContext.setLastException(exception);
            log.info(driver.getPageSource());
            throw new TimeoutException("Element not found after waiting for " + waitSec + " seconds: " + element, exception);
        }
    }


    public WebElement waitUntilVisible(By by, int waitSec) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(waitSec));
        try {
            log.info(LOOKING_FOR_ELEMENT, by);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            WebElement element = driver.findElement(by);
            return element;
        } catch (NoSuchElementException | TimeoutException exception) {
            TestContext.setLastException(exception);
            log.info(driver.getPageSource());
            throw new TimeoutException("Element not found after waiting for " + waitSec + " seconds: " + by, exception);
        }
    }

    protected void waitUntilElementHasText(By by) {
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(5))).until((ExpectedCondition<Boolean>) d -> d.findElement(by).getText().length() != 0);
        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Text didn't appear in element: '{}'", by, e);
            TestContext.setLastException(e);
            log.info(driver.getPageSource());
            throw new TimeoutException("Text didn't appear in element: " + by, e);
        }
    }

    protected void waitUntilElementHasText(By webElementBy, String expectedText) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT));
        webDriverWait.until(ExpectedConditions.textToBe(webElementBy, expectedText));
    }

    protected void waitUntilInputElementIsEmpty(WebElement webElementBy) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT));
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(webElementBy, ""));
    }

    protected void waitUntilElementHasText(By webElementBy, String expectedText, long timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        webDriverWait.until(ExpectedConditions.textToBe(webElementBy, expectedText));
    }

    protected void waitUntilElementContainsExpectedText(By by, String expectedText) {
        waitUntilElementHasTextSuppressingException(by, expectedText, 5);
    }

    protected void waitUntilElementHasTextSuppressingException(By by, String expectedText, long time) {
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(time)))
                    .ignoring(StaleElementReferenceException.class)
                    .until((ExpectedCondition<Boolean>) d -> d.findElement(by).getText().contains(expectedText));
        } catch (TimeoutException e) {
            log.error("Element: {} does not contain text: {}", by, expectedText, e);
            TestContext.setLastException(e);
            throw new TimeoutException("Element: " + by + " does not contain text: " + expectedText, e);
        }
    }

    protected void waitUntilElementAttributeHasSomeValue(WebElement element, String attribute) {
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(2))).until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
        } catch (NoSuchElementException | TimeoutException e) {
            log.info("Element not found or element is empty", e);
        }
    }

    protected String waitUntilElementHasTextAndGet(By by) {
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(10))).until((ExpectedCondition<Boolean>) d -> d.findElement(by).getText().length() != 0);
            return driver.findElement(by).getText();
        } catch (TimeoutException e) {
            log.error("Could not locate element", e);
            TestContext.setLastException(e);
        }
        return "";
    }

    protected void tryToWaitUntilElementIsEnabled(By by, int waitForSeconds) {
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds))).until((ExpectedCondition<Boolean>) d -> d.findElement(by).isEnabled());
        } catch (NoSuchElementException | TimeoutException e) {
            log.info("Could not locate element or element was not enabled: {}", by, e);
            TestContext.setLastException(e);
            throw new TimeoutException("Element not found after waiting for " + LOAD_WAIT + " seconds: " + e);
        }
    }

    protected String getText(WebElement element, int wait) {
        try {
            return waitUntilVisible(element, wait).getText();
        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Could not locate element", e);
            TestContext.setLastException(e);
        }
        return "";
    }

    protected String getText(WebElement element) {
        return getText(element, LOAD_WAIT);
    }

    protected String getTextIgnoringStaleElementReferenceException(By locator) {
        try {
            return getText(locator, LOAD_WAIT);
        } catch (StaleElementReferenceException ex) {
            return getText(locator, LOAD_WAIT);
        }
    }


    protected String getText(By by, int wait) {
        try {
            return waitUntilVisible(by, wait).getText();
        } catch (NoSuchElementException | TimeoutException e) {
            TestContext.setLastException(e);
            log.error("Could not locate element", e);
        }
        return "";
    }

    protected String getText(By by) {
        return getText(by, LOAD_WAIT);
    }

    protected String extractText(WebElement element, int wait) {
        try {
            WebElement el = waitUntilVisible(element, wait);

            String text = el.getText();
            if (text != null && !text.isBlank()) {
                return text.trim();
            }
            String contentDesc = el.getAttribute("contentDescription");
            if (contentDesc != null && !contentDesc.isBlank()) {
                return contentDesc.trim();
            }

        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Could not extract text from element", e);
            TestContext.setLastException(e);
            throw new NoSuchElementException(e.getMessage());
        }

        return "";
    }

    private Boolean waitUntilBodyHasNoModal(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT))
                .until(ExpectedConditions.refreshed(ExpectedConditions.attributeToBe(element, CLASS_ATTRIBUTE, "")));
    }

    private Boolean waitUntilBodyHasModal(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT))
                .until(ExpectedConditions.refreshed(ExpectedConditions.attributeContains(element, CLASS_ATTRIBUTE, "modal-open")));
    }

    protected void waitUntilModalIsOpened() {
        waitUntilBodyHasModal(body);
    }

    protected void waitUntilModalIsClosed() {
        waitUntilBodyHasNoModal(body);
    }

    public boolean isModalContentOpened() {
        waitUntilVisible(MODAL_CONTENT_BY);
        return !modalContentOpen.isEmpty();
    }

    public boolean isModalContentNotVisible() {
        waitUntilNotVisible(MODAL_CONTENT_BY);
        return modalContentOpen.isEmpty();
    }

    protected Boolean waitUntilNotVisible(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected Boolean waitUntilModalNotVisible() {
        return new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT)).until(ExpectedConditions.invisibilityOfElementLocated(MODAL_CONTENT_BY));
    }

    protected Boolean waitUntilNotVisible(By by, int waitSec) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitSec)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public Boolean waitUntilElementDisappear(WebElement element, int waitSec) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitSec)).until(ExpectedConditions.invisibilityOf(element));
    }

    protected void clickOnElementAndWaitToDisappearWithRetry(By by, int waitSec) {
        waitUntilVisible(by).click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(waitSec)).until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            log.error("Element didn't disappear after clicking it. Trying to click again.");
            driver.findElement(by).click();
        }
    }

    public void clickWithFocusAndWait(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).perform();
        bringFocusOnElement(webElement);
        waitUntilVisible(webElement);
        waitUntilClickable(webElement).click();
    }

    protected WebElement waitUntilVisible(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected WebElement waitUntilVisibleAndEnabled(WebElement element) {
        waitUntilVisible(element);
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.attributeToBe(element, "class", "primary"));
        return element;
    }

    protected WebElement waitUntilClickable(WebElement element) {

        log.info(LOOKING_FOR_ELEMENT, element);
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT));
            webDriverWait.pollingEvery(Duration.ofSeconds(2))
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));

            return element;
        } catch (NoSuchElementException | TimeoutException exception) {
            TestContext.setLastException(exception);
            log.info(driver.getPageSource());
            throw new TimeoutException("Element not found after waiting for " + LOAD_WAIT + " seconds: " + element, exception);
        }
    }

    public void waitUntilElementDisappear(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            log.info(driver.getPageSource());
            TestContext.setLastException(e);
            throw new TimeoutException("Element not found after waiting for " + LOAD_WAIT + " seconds: " + e);

        }
    }

    protected WebElement waitUntilClickable(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT))
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void clickWithRetry(By by) {
        RetryUtils.retry(NUMERIC_4, () ->
                        driver.findElement(by).click(),
                "Exception thrown during click retry attempt");
    }

    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }


    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            log.info("ALERT IS PRESENT");
            return true;
        } catch (NoAlertPresentException exception) {
            return false;
        }
    }

    protected void acceptAlertIfPresent() {
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
    }

    public void waitForAlertIfPresentAndAccept(int waitTime) {
        try {
            await().atMost(waitTime, SECONDS)
                    .pollInterval(1, SECONDS)
                    .until(this::isAlertPresent);
        } catch (ConditionTimeoutException e) {
            log.info(e.getMessage());
            log.info("Done waiting for alert to be displayed");
        }
        acceptAlertIfPresent();

    }

    public void dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException ignored) {
        }
    }

    public boolean isAlertDialogAppear() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.getText();
            return alert.getText().contains("cancel");
        } catch (NoAlertPresentException ignored) {
            return false;
        }
    }

    protected void clearAutoFilledField(WebElement element) {
        element.clear();
        if (System.getProperty("os.name").contains("Mac")) {
            element.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
        } else {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        }
        //not always possible to get value of the element,
        // but the field should be cleaned,
        // this step is working for input phone number clean up
        for (int i = 0; i < 25; i++)
            element.sendKeys(Keys.BACK_SPACE);
        waitUntilInputElementIsEmpty(element);
    }

    protected boolean isClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isButtonStateChangedToBeEnabled(WebElement element) {
        for (int i = 0; i < 5; i++) {
            if (!element.isEnabled()) {
                waitForMilliSecs(100);
            } else {
                break;
            }
        }
        return element.isEnabled();
    }

    protected String getAccessToken() {
        try {
            return driver.manage().getCookieNamed("verimi-access-token").getValue();
        } catch (Exception e) {
            log.error("Couldn't get access token.", e);
            return "";
        }
    }

    protected void scrollToElementNotAlignToTop(WebElement element) {
        waitUntilVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", element);
        waitForMilliSecs(500);
    }

    protected void scrollToElement(By by) {
        scrollToElement(driver.findElement(by));
    }

    protected void scrollToElement(WebElement element) {
        waitUntilVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        waitForMilliSecs(WAIT_500);
    }

    protected void scrollToCenter(WebElement element) {
        waitUntilVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({" +
                "behavior: 'auto'," +
                "block: 'center'," +
                "inline: 'center'" +
                "})", element);
        waitForMilliSecs(WAIT_500);
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForMilliSecs(WAIT_500);
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        waitForMilliSecs(WAIT_500);
    }

    public void waitUntilLoadingScreenNotVisible() {
        waitUntilLoadingScreenNotVisible(200);
    }

    public void waitUntilLoadingScreenNotVisible(int time) {
        waitUntilNotVisible(loading);
        waitForMilliSecs(time);
    }

    public void submitForm() {
        submitButton.click();
    }

    public String getValueAttribute(WebElement webElement) {
        return webElement.getAttribute(VALUE_ATTRIBUTE);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public Object bringFocusOnElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return jse.executeScript("arguments[0].focus();", element);
    }

    public WebElement findElement(By byLocator) {
        return driver.findElement(byLocator);
    }

    public static void sendRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = new OkHttpClient().newCall(request).execute();
            log.info("Request was sent to: {}", url);
            log.info("Status code is: {}", response.code());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected <T> T newPageInstance(Class<T> klass) {
        if (klass != null) {
            try {
                return klass.getConstructor(WebDriver.class).newInstance(driver);
            } catch (AssertionError e) {
                throw e;
            } catch (InstantiationException | NoSuchMethodException e) {
                throw new RuntimeException(
                        String.format("Constructor that takes only WebDriver parameter is not found in class %s",
                                klass.getSimpleName()), e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(
                        String.format("Error creating instance of class %s. Constructor threw exception",
                                klass.getSimpleName()), e);
            } catch (IllegalAccessException e) {
                log.info(driver.getPageSource());
                TestContext.setLastException(e);
                throw new RuntimeException(
                        String.format("Unable to create instance of class as constructor for class %s isn't accessible",
                                klass.getSimpleName()), e);
            }
        }
        return null;
    }

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public void openNewTabAndSwitch(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open('','blank');");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));
        BrowserTabOperation browserTabOperation = new BrowserTabOperation(driver);
        browserTabOperation.waitForExpectedTabCount(2);
        browserTabOperation.switchBrowserTab(getCurrentWindowHandle());
        driver.get(url);
    }

    public void switchBrowserTab() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));
        BrowserTabOperation browserTabOperation = new BrowserTabOperation(driver);
        browserTabOperation.waitForExpectedTabCount(2);
        browserTabOperation.switchBrowserTab(getCurrentWindowHandle());
    }

    public String getInnerHtml(WebElement element) {
        return element.getAttribute("innerHTML");
    }

    public boolean isTextAvailable(String text) {
        return driver.getPageSource().contains(text);
    }

    public void switchToNewWindow(String currentWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.close();
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void clickConfirmOnModal() {
        waitUntilVisible(confirmModal).click();
    }

    public List<WebElement> getLogos() {
        return partnerLogos;
    }

    /**
     * Injects image to the camera from BrowserStack uploaded media files
     *
     * @param mediaId image to inject
     */
    public void injectImage(String mediaId) {
        if (CloudProvider.isCloudRun()) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("{\"action\":\"cameraImageInjection\", " +
                    "\"arguments\": {\"imageUrl\" : \"media://" + mediaId + "\"}}");
        } else {
            log.info("Skipping camera injection, it is not working on local run!");
        }
    }

    public void switchToNewTabByPositionWithoutClosing(int tabPosition) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - tabPosition));
    }

    protected WebElement waitUntilOneElementVisible(WebElement locator1, WebElement locator2) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(LOAD_WAIT));
        wait.pollingEvery(Duration.ofSeconds(1));
        WebElement visibleElement = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    // Check if element1 is visible
                    if (locator1.isDisplayed()) {
                        return locator1;
                    }
                } catch (Exception e) {
                    TestContext.setLastException(e);
                    // Element1 not found or not visible
                }
                try {
                    // Check if element2 is visible
                    if (locator2.isDisplayed()) {
                        return locator2;
                    }
                } catch (Exception e) {
                    TestContext.setLastException(e);
                    // Element2 not found or not visible
                }
                return null;
            }
        });
        return visibleElement;
    }

    protected boolean isDisplayedAndClickable(String locator) {
        return !driver.findElements(By.id(locator)).isEmpty() && driver.findElement(By.id(locator)).isDisplayed();
    }

    public String getHrefAttribute(WebElement webElement) {
        return webElement.getAttribute("href");
    }

    protected void clearBrowserState() {
        try {
            driver.manage().deleteAllCookies();
            ((JavascriptExecutor) driver).executeScript(
                    "window.localStorage && window.localStorage.clear(); " +
                            "window.sessionStorage && window.sessionStorage.clear();"
            );
        } catch (Exception e) {
            log.warn("Ignoring cleanup exception: {}", e.getMessage());
        }
    }

    protected void clickAndWaitUntilChecked(WebElement checkbox) {
        waitUntilVisible(checkbox);
        String expectedValue = String.valueOf(NUMERIC_1);
        if (!expectedValue.equals(getValueAttribute(checkbox))) {
            waitUntilClickable(checkbox).click();
        }

        waitUntilAttributeToBe(checkbox, VALUE_ATTRIBUTE, expectedValue);
    }

}
