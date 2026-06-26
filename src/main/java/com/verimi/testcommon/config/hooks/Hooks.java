package com.verimi.testcommon.config.hooks;

import static com.verimi.testcommon.config.Config.PLATFORM_NAME;
import static com.verimi.testcommon.config.Config.isAndroid;
import static com.verimi.testcommon.config.Config.isIOS;
import static com.verimi.testcommon.config.Config.isIpad;
import static com.verimi.testcommon.framework.report.TestExecutionRegistry.CLOUD_REPORT_URL_ATTRIBUTE;
import static com.verimi.testcommon.framework.utils.cookie.CookieUtilities.LANGUAGE_COOKIE;
import static com.verimi.testcommon.model.common.Locale.de_DE;
import static com.verimi.testcommon.model.common.Locale.getLocale;
import static com.verimi.testcommon.model.common.Platform.ANDROID;
import static com.verimi.testcommon.model.common.Platform.IOS;
import static com.verimi.testcommon.model.common.Platform.IPAD;
import static com.verimi.testcommon.model.common.Platform.WINDOWS;
import static com.verimi.testcommon.pageobject.mobile.MobileScreen.ANDROID_CHROME_PACKAGE;
import static com.verimi.testcommon.pageobject.mobile.MobileScreen.IOS_SAFARI_PACKAGE;
import static com.verimi.testcommon.pageobject.web.Page.NATIVE_APP;
import static com.verimi.testcommon.pageobject.web.Page.WEBVIEW_CHROME;
import static io.restassured.RestAssured.given;
import static java.lang.Double.parseDouble;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IConfigurable;
import org.testng.IConfigureCallBack;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Uninterruptibles;
import com.verimi.testcommon.config.Config;
import com.verimi.testcommon.config.cloudprovider.BrowserStackUtil;
import com.verimi.testcommon.config.cloudprovider.CloudProvider;
import com.verimi.testcommon.framework.asserts.DippAssertions;
import com.verimi.testcommon.framework.asserts.DippSoftAssertions;
import com.verimi.testcommon.framework.drivers.BaseRemoteWebDriverProvider;
import com.verimi.testcommon.framework.drivers.DriverManager;
import com.verimi.testcommon.framework.drivers.DriverProvider;
import com.verimi.testcommon.framework.report.TestExecutionRegistry;
import com.verimi.testcommon.framework.report.TestSide;
import com.verimi.testcommon.framework.utils.cloud.CloudUrlReference;
import com.verimi.testcommon.framework.utils.cookie.CookieUtilities;
import com.verimi.testcommon.framework.utils.dbutils.DBConnectionManager;
import com.verimi.testcommon.framework.utils.exceptions.PlatformNotFoundException;
import com.verimi.testcommon.framework.utils.json.JsonUtil;
import com.verimi.testcommon.framework.utils.kubernetes.KubernetesUtils;
import com.verimi.testcommon.framework.utils.logging.LogUtils;
import com.verimi.testcommon.framework.utils.logging.TestLogHelper;
import com.verimi.testcommon.framework.utils.retry.RetryUtils;
import com.verimi.testcommon.framework.utils.timeout.Timeout;
import com.verimi.testcommon.model.common.Locale;
import com.verimi.testcommon.pageobject.mobile.MobileBrowserScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.appmanagement.AndroidTerminateApplicationOptions;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks implements IConfigurable {
    public static final String MOBILE_SAFARI_BROWSER = "com.apple.mobilesafari";
    public static final String DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID = "de.bund.bmds.kivp.app.dev";
    public static final String DEUTSCHLAND_APP_BUNDLE_ID_ANDROID = "de.bund.bmds.kivp.app";
    private static final int IOS_APP_STATE_FOREGROUND = 4;
    public final int BEFORE_TEST_RETRIES_COUNT = 4;
    private static final int SCREEN_WIDTH = 1920;
    private static final int SCREEN_HEIGHT = 1080;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final String JS_PLATFORM_DETECTOR = "return navigator.platform;";
    private static final String JS_PLATFORM_MACOS = "MacIntel";
    private static final String JS_PLATFORM_WINDOWS = "Win32";
    private static final String JS_PLATFORM_LINUX = "Linux x86_64";
    private static final String SCREENSHOT_LOCATION = "build" + File.separator + "test-report" + File.separator + "screenshots";
    public static String jobDuration;
    protected static DippAssertions dippAssertions = new DippAssertions();
    @Setter
    @Getter
    protected DippSoftAssertions softAssertion;
    private Method methodName;
    private CookieFilter cookieFilter;

    private WebDriver driver = null;

    private BaseRemoteWebDriverProvider remoteWebDriverProvider;

    // todo move to proper class
    public static boolean isMobilePlatform() {
        return PLATFORM_NAME == ANDROID || PLATFORM_NAME == IOS || PLATFORM_NAME == IPAD;
    }

    public static String extractLoginChallenge(String location) {
        Pattern pattern = Pattern.compile(".*login_challenge=(.*)");
        Matcher matcher = pattern.matcher(location);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Unable to extract login challenge from " + location);
    }

    public static String changeToApiUrl(String url) {
        if (Config.isDev1()) {
            return url.replace("www-", "api-")
                    .replace("web.", "api.");
        } else {
            return url;
        }
    }

    @SneakyThrows
    @Before()
    public void setUp(Scenario scenario) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String language = System.getenv("LOCALE");
        if (language == null) {
            language = de_DE; // Default locale is set to German
        }
        Locale.setLocale(language);
        log.info("Locale used for testing: {}", language);
        TestExecutionRegistry.setTestSide(TestSide.FRONTEND);
        log.info("Scenario name: {}", scenario.getName());
        initWebdriver(scenario.getName());
        driver = getDriver();

        log.info("Initialized WebDriver in Before hook for the scenario: {}", scenario.getName());
        DriverManager.setDriver(driver);
    }

    @After(order = Integer.MAX_VALUE)
    public void teardownScenario(Scenario scenario) {
        if (driver != null) {
            BrowserStackUtil.captureFailure((AppiumDriver) driver, scenario);
            BrowserStackUtil.updateScenarioStatus((AppiumDriver) driver, scenario);
            driver.quit();
        }
    }


    public void terminateAndOpenDeutschlandApp() {
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            String packageName = androidDriver.getCurrentPackage();
            if (packageName == null || !packageName.contains(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID)) {
                openDeutschlandApp();
            }
            for (int i = 0; i < 3; i++) {
                try {
                    clearAppData();

                    log.info("Initiating Deutschland app termination with package name: {}", androidDriver.getCurrentPackage());
                    androidDriver.terminateApp(
                            DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID,
                            new AndroidTerminateApplicationOptions()
                                    .withTimeout(Duration.ofSeconds(10)));
                    log.info("Deutschland app terminated successfully");
                    break;
                } catch (WebDriverException e) {
                    if (i == 2) throw e;
                    // Static wait to wait for app to be terminated
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
            openDeutschlandApp();

        } else if (isIOS()) {
            clearAppData();
            IOSDriver iosDriver = ((IOSDriver) driver);
            iosDriver.terminateApp(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID);
            openDeutschlandApp();
        }
    }

    public void clearAppData() {
        log.info("Clear app data");
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            String packageName = androidDriver.getCurrentPackage();
            if (!packageName.contains(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID)) {
                packageName = DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID;
            }
            androidDriver.executeScript("clearApp", Map.of("appId", packageName));
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        } else if (isIOS()) {
            // iOS does not provide a direct way to clear app data, but reinstalling the app helps.
            IOSDriver iosDriver = ((IOSDriver) driver);
            log.info("iOS does not support clearing app data programmatically. Ensure that the app is deleted");
            String bundleId =
                    iosDriver.getCapabilities()
                            .getCapability("bundleId")
                            .toString();

            iosDriver.terminateApp(bundleId);
            iosDriver.removeApp(bundleId);
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        }

    }

    public void openDeutschlandApp() {

        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            try {
                androidDriver.activateApp(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID);
            } catch (WebDriverException e) {
                androidDriver.activateApp(DEUTSCHLAND_APP_BUNDLE_ID_ANDROID);
            }
            androidDriver.rotate(ScreenOrientation.PORTRAIT);
        } else if (isIOS()) {
            IOSDriver iosDriver = ((IOSDriver) driver);
            iosDriver.terminateApp(DEUTSCHLAND_DEV_APP_BUNDLE_ID_ANDROID);

        }
        log.info("Opened Deutschland App successfully");
    }

    @BeforeMethod(alwaysRun = true)
    public void setTestAttributes(Method method, ITestResult testResult) {
        this.methodName = method;
        TestLogHelper.setTestName(methodName.getName());
        log.info("Starting {} test", methodName);
    }

    @BeforeMethod(alwaysRun = true)
    public void addCloudReportUrl(ITestResult testResult) {
        CloudUrlReference cloudReportUrlReference = () -> {
            if (CloudProvider.isCloudRun() && driver != null) {
                return CloudProvider.getInstance().logReportUrl(((RemoteWebDriver) getDriver()).getSessionId().toString());
            }
            return null;
        };
        testResult.setAttribute(CLOUD_REPORT_URL_ATTRIBUTE, cloudReportUrlReference);
    }

    /**
     * Instead of creation regular or soft assertions each time before method we do it here
     */
    @BeforeMethod(alwaysRun = true)
    public void createAssertions() {
        softAssertion = new DippSoftAssertions();
    }

    @AfterMethod(alwaysRun = true)
    public void logTestFinishes(Method method) {
        log.info("Finishing {} test", method.getName());
    }

    /**
     * This method is expected assert soft assertions and throw exception after the test automatically.
     * Currently disabled as reporter don't catch this exception (see VD-10119).
     * Call it manually after test finishes
     */
    @AfterMethod(enabled = false)
    public void assertAll() {
        if (softAssertion != null) {
            softAssertion.assertAll();
        }
    }

    /**
     * After each test we need to cleanup created user and authentication data
     * to avoid mixing with different tests
     */
    @AfterMethod(alwaysRun = true)
    public void clearUser() {
        cookieFilter = null;
        TestLogHelper.removeTestName();
    }

    @AfterMethod(alwaysRun = true)
    @SneakyThrows
    public void postMethodActions(Method method, ITestResult testResult) {
        if (CloudProvider.isCloudRun() && driver != null) {
            takeScreenShotOnFailure(testResult);
            String sessionId = ((RemoteWebDriver) getDriver()).getSessionId().toString();
            CloudProvider.getInstance().setTestResult(testResult, sessionId);
            try {
                if (driver != null) {
                    getDriver().quit();
                }
            } catch (Exception e) {
                log.error("[SKIP_DEBUG] Driver closure exception, probably was already closed", e);
                log.error("[SKIP_DEBUG] Driver closure exception, probably was already closed, cause: ", e.getCause());
            } finally {
                driver = null;
            }

            if (remoteWebDriverProvider != null) {
                remoteWebDriverProvider.stopCloudTunnel();
                remoteWebDriverProvider = null;
            }
        } else {
            if (null != driver) {
                closeAlertIfPresent();
                takeScreenShotOnFailure(testResult);
                getDriver().quit();
                driver = null;
            }
        }
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                log.error("[SKIP_DEBUG] Driver closure exception, probably was already closed", e);
                log.error("[SKIP_DEBUG] Driver closure exception, probably was already closed, cause: ", e.getCause());
            } finally {
                driver = null;
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuiteMethods(org.testng.ITestContext context) {
        if (null != context) {
            String[] groups = context.getIncludedGroups();
            for (String group : groups) {
                if ("".equals(group)) {
                    return;
                }
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    public void setJobDuration() {
        String gitLabReadToken = getenv("GL_BUILD_READ_API");
        if (gitLabReadToken != null) {
            Response response = given()
                    .header("PRIVATE-TOKEN", gitLabReadToken)
                    // 102 is project id for system-test
                    .get("https://gitlab.verimi.cloud/api/v4/projects/102/jobs/" + getProperty("buildNumber"));
            log.info("Job info is: {}", response.asString());

            double secondsDouble = parseDouble(JsonUtil.getJsonValue(response.asString(), "duration"));
            long totalSeconds = Math.round(secondsDouble);

            long minutes = totalSeconds / SECONDS_PER_MINUTE;
            long seconds = totalSeconds % SECONDS_PER_MINUTE;

            jobDuration = String.format("%d:%02d", minutes, seconds);
        }
    }

    @Parameters({"locale"})
    @BeforeSuite(alwaysRun = true)
    public void setupSuite(org.testng.ITestContext context, @Optional(de_DE) String locale) throws Exception {
        //Verimi uses UTC timezone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Locale.setLocale(locale);
        log.info("Locale used for testing: {}", locale);
        TestExecutionRegistry.setTestSide(TestSide.FRONTEND);
        createOrCleanupScreenshotsFolder();
    }

    private void createOrCleanupScreenshotsFolder() throws Exception {
        final File dir = new File(SCREENSHOT_LOCATION);
        if (dir.exists()) {
            org.apache.commons.io.FileUtils.cleanDirectory(new File(SCREENSHOT_LOCATION));
        } else {
            org.apache.commons.io.FileUtils.forceMkdir(new File(SCREENSHOT_LOCATION));
        }
    }

    private void setupBrowser(String methodName) throws Exception {
        log.info("Test running on environment: {}", System.getenv("DIPP_TEST_ENV"));
        if (CloudProvider.isCloudRun()) {
            log.info("Using cloud provider to run the tests");
            remoteWebDriverProvider = (BaseRemoteWebDriverProvider) DriverProvider.getRemoteProvider(methodName);
            driver = remoteWebDriverProvider.startRemote();
        } else {
            if (PLATFORM_NAME == WINDOWS) {
                log.info("Running tests on Desktop.....");
            } else {
                log.info("Running tests on mobile device....");
            }
            driver = DriverProvider.getLocalProvider().start();
        }
    }

    public void initWebdriver(String methodName) throws Exception {
        log.info(LogUtils.createMessageWithBorder("Executing test case: " + methodName, "="));
        setupBrowser(methodName);
        log.info("Page load timeout is set to {} seconds.", Timeout.DEFAULT_PAGE_LOAD_TIME);
        if (!isMobilePlatform()) {
            try {
                CookieUtilities.setInitialCookies(getDriver());
            } catch (WebDriverException exception) {
                throw new WebDriverException("Failed to set cookie: '" + CookieUtilities.TEST_COOKIE + "'. Hint: environment may not be available.", exception);
            }
        }
    }

    private void closeAlertIfPresent() {
        acceptAlert();
        dismissAlert();
    }

    private void dismissAlert() {
        if (getDriver() != null) {
            try {
                Alert alert = getDriver().switchTo().alert();
                alert.dismiss();
                log.info("Alert dialog found and accepted during loading the frontend URL. Alert dismissed.");
            } catch (Exception e) {
                //Do nothing, alert is not expected to appear
            }
        }
    }

    public void acceptAlert() {
        if (getDriver() != null) {
            try {
                Alert alert = getDriver().switchTo().alert();
                alert.accept();
                log.info("Alert dialog found and accepted during loading the frontend URL. Alert accepted.");
            } catch (Exception e) {
                //Do nothing, alert is not expected to appear
            }
        }
    }

    protected void resizeBrowser(WebDriver driver) throws PlatformNotFoundException {
        Object platformResult = ((JavascriptExecutor) driver).executeScript(JS_PLATFORM_DETECTOR);
        if (null != platformResult) {
            String platform = platformResult.toString();
            if (platform.contains(JS_PLATFORM_MACOS) || platform.contains(JS_PLATFORM_LINUX)) {
                driver.manage().window().setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            } else if (platform.contains(JS_PLATFORM_WINDOWS)) {
                driver.manage().window().maximize();
            } else {
                throw new PlatformNotFoundException(platform);
            }
        }
    }

    public void waitUntilUrlContains(String path) throws SQLException {
        RetryUtils.tryToWaitUntilConditionFulfilled(() -> getDriver().getCurrentUrl().contains(path),
                80,
                500,
                "Waiting for URL to contain '" + path + "'. Current URL is: " + getDriver().getCurrentUrl());
    }

    public void waitUntilUrlContains(String path, int waitMilliSecs) throws SQLException {
        RetryUtils.tryToWaitUntilConditionFulfilled(() -> getDriver().getCurrentUrl().contains(path),
                40,
                waitMilliSecs,
                "Waiting for URL to contain '" + path + "'. Current URL is: " + getDriver().getCurrentUrl());
    }

    private void takeScreenShotOnFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE && getDriver() != null) {
            File screenShotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            long paramsHashCode = Stream.of(testResult.getParameters())
                    .mapToLong(o -> o != null ? o.hashCode() : 0)
                    .sum();
            try {
                org.apache.commons.io.FileUtils.copyFile(screenShotFile, new File(SCREENSHOT_LOCATION + "/" + testResult.getInstanceName()
                        .replace("", "") + "-" + testResult.getName() + paramsHashCode + ".jpg"));
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    public boolean isIOSDriver() {
        return driver instanceof IOSDriver;
    }

    public boolean isAndroidDriver() {
        return driver instanceof AndroidDriver;
    }

    public void rotateDeviceToLandscapeMode() {
        if (isIOSDriver()) {
            ((IOSDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        } else if (isAndroidDriver()) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.LANDSCAPE);
        }
    }

    public void rotateDeviceToPortraitMode() {
        if (isIOSDriver()) {
            ((IOSDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        } else if (isAndroidDriver()) {
            ((AndroidDriver) driver).rotate(ScreenOrientation.PORTRAIT);
        }
    }

    public void reOpenPage(String url) {
        if (isMobilePlatform()) {
            if (isIOS()) {
                ((IOSDriver) getDriver()).executeScript("launchApp", ImmutableMap.of("bundleId", MOBILE_SAFARI_BROWSER));
                List args = new ArrayList();
                args.add("-U");
                args.add(url);

                Map<String, Object> params = new HashMap<>();
                params.put("bundleId", MOBILE_SAFARI_BROWSER);
                params.put("arguments", args);
                ((IOSDriver) getDriver()).executeScript("activateApp", params);
            } else if (isAndroid()) {
                openPage(url);
            } else {
                getDriver().get(url);
            }
        }
    }

    public void openPage(String url) {
        if (isMobilePlatform()) {
            if (isIOS()) {
                //unfortunately getDriver().get(url) doesn't work for real devices runs
                //see details https://gist.github.com/biwkf/b0ebb9940e6341ed5e588f148b4381a8
                ((IOSDriver) getDriver()).executeScript("terminateApp", ImmutableMap.of("bundleId", MOBILE_SAFARI_BROWSER));
                List args = new ArrayList();
                args.add("-U");
                args.add(url);

                Map<String, Object> params = new HashMap<>();
                params.put("bundleId", MOBILE_SAFARI_BROWSER);
                params.put("arguments", args);
                ((IOSDriver) getDriver()).executeScript("launchApp", params);
            } else if (isIpad()) {
                getDriver().get(url);
            } else {
                AndroidDriver driver = ((AndroidDriver) getDriver());
                driver.activateApp("com.android.chrome");
                driver.rotate(ScreenOrientation.PORTRAIT);
                await().atMost(10, SECONDS)
                        .pollInterval(1, SECONDS)
                        .until(() -> driver.getContextHandles().contains(WEBVIEW_CHROME));// waiting for launching chrome
                driver.context(WEBVIEW_CHROME);
                log.info("The context was switched to: " + driver.getContext());
                getDriver().get(url);
            }
        } else {
            getDriver().get(url);
        }
    }

    public MobileBrowserScreen openNewMobileBrowser() {
        if (isMobilePlatform()) {
            if (isIOS()) {
                IOSDriver driver = ((IOSDriver) getDriver());
                driver.terminateApp(IOS_SAFARI_PACKAGE);
                driver.activateApp(IOS_SAFARI_PACKAGE);
                return new MobileBrowserScreen(driver);
            } else {
                AndroidDriver driver = ((AndroidDriver) getDriver());
                driver.terminateApp(ANDROID_CHROME_PACKAGE);
                driver.activateApp(ANDROID_CHROME_PACKAGE);
                return new MobileBrowserScreen(driver);
            }
        } else {
            throw new IllegalStateException("This method is for mobile only!");
        }
    }

    public String currentUrl() {
        return getDriver().getCurrentUrl();
    }

    protected void setContextToNative() {
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) getDriver());
            ((AndroidDriver) getDriver()).context(NATIVE_APP);
            log.info("The context was switched to: " + androidDriver.getContext());
        } else {
            IOSDriver iOSDriver = ((IOSDriver) getDriver());
            ((IOSDriver) getDriver()).context(NATIVE_APP);
            log.info("The context was switched to: " + iOSDriver.getContext());
        }
    }

    /**
     * Delays WebDriver creation till it is needed
     * i.e. BE tests do not need WebDriver
     *
     * @return
     */
    public WebDriver getDriver() {
        if (driver == null) {
            synchronized (this) {
                try {
                    initWebdriver(methodName.getName());
                } catch (Exception e) {
                    log.error("Caught exception during WebDriver initialization", e);
                    log.error("Caught exception during WebDriver initialization. Cause: ", e.getCause());
                    throw new RuntimeException(e);
                }
            }
        }
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private Response get(String path) {
        return given()
                .redirects().follow(false)
                // .config(restAssuredConfig)
                .cookie(LANGUAGE_COOKIE, getLocale())
                .filter(cookieFilter)
                .log()
                .all()
                .get(path)
                .thenReturn();
    }


    /**
     * Close database connection
     */
    @AfterSuite(alwaysRun = true)
    public void closeConnections() {
        log.info("Closing database connections.");
        DBConnectionManager.closeAllConnections();
    }

    @AfterSuite(alwaysRun = true)
    public void closePortForwards() {
        KubernetesUtils.closePortForwards();
    }

    public void closeAndOpenNewTab() {
        ((JavascriptExecutor) getDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().close();
        getDriver().switchTo().window(tabs.get(1));
    }


    public boolean isAppInBackground() {
        IOSDriver iOSDriver = (IOSDriver) driver;

        String bundleId = (String) iOSDriver.getCapabilities().getCapability("bundleId");

        Map<String, Object> args = new HashMap<>();
        args.put("bundleId", bundleId);

        Long state = (Long) iOSDriver.executeScript("queryAppState", args);
        log.info("App state = {}", state);

        // foreground = 4 → NOT in background
        // anything else → not in foreground
        return state != IOS_APP_STATE_FOREGROUND;
    }

    /**
     * Clears cookies + local/session storage to ensure the next flow starts in a fresh browser state.
     */
    protected void clearBrowserState() {
        if (driver == null) {
            return;
        }

        log.info("Clearing browser session: cookies + localStorage + sessionStorage");
        driver.manage().deleteAllCookies();

        try {
            ((JavascriptExecutor) getDriver()).executeScript(
                    "window.localStorage && window.localStorage.clear();" +
                            "window.sessionStorage && window.sessionStorage.clear();"
            );
        } catch (Exception e) {
            log.warn("Unable to clear local/session storage via JS. Reason: {}", e.getMessage());
        }
    }

    public void run(IConfigureCallBack callBack, ITestResult testResult) {
        callBack.runConfigurationMethod(testResult);
        if (testResult.getThrowable() != null) {
            log.warn("Caught exception in method {}: {}", testResult.getMethod(), testResult.getThrowable().getMessage(),
                    testResult.getThrowable());
            if (driver != null) {
                driver.quit();
                setDriver(null);
            }

            for (int i = 0; i < BEFORE_TEST_RETRIES_COUNT; i++) {
                testResult.setThrowable(null);
                log.warn("Retrying attempt #{} method {}", i, testResult.getMethod());
                callBack.runConfigurationMethod(testResult);
                if (testResult.getThrowable() == null) {
                    break;
                }
                if (driver != null) {
                    driver.quit();
                    setDriver(null);
                }
            }
        }
    }

}
