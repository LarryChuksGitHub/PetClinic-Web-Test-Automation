package com.verimi.testcommon.framework.utils.cookie;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.verimi.testcommon.model.common.TestCookieContent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtilities {

    public static final String TEST_COOKIE = "verimi-test-in-progress";
    public static final String DEBUG_COOKIE = "verimi-log-debug";
    public static final String DIPP_TOKEN = "dipp-token";
    public static final String LOCALE = "locale";
    public static final String ALL = "*/*";
    public static final String EN = "en";
    public static final String DE = "de";
    public static final String GERMANY_2_LETTER_CODE = "DE";

    private static final String ROBOTS_TEXT_CONTENT = "User-agent: *\n" + "Disallow: /";
    public static final String LANGUAGE_COOKIE = "language";

    private CookieUtilities() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isRobotsTextValid(WebDriver driver) {
        WebElement body = driver.findElement(By.tagName("body"));
        String innerText = body.getText();
        log.info("body text : {}", innerText);
        return innerText.equals(ROBOTS_TEXT_CONTENT);
    }

    public static void clearCacheAndCookies(WebDriver driver) {
        log.info("Deleting cache and cookies...");
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("sessionStorage.clear(); localStorage.clear();");
    }

    public static void getCookies(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        log.info("We have: {} cookies", cookies.size());

        for (Cookie cookie : cookies) {
            Boolean isSecure = cookie.isSecure();
            Boolean isHttpOnly = cookie.isHttpOnly();
            log.info("\n\tCookie name: {}\tCookie path: {}\tCookie domain: {}\tCookie value: {}\tCookie expiry: {}"
                            + "\tSecure cookie: {}\tHTTP only cookie: {}",
                    cookie.getName(), cookie.getPath(), cookie.getDomain(), cookie.getValue(),
                    cookie.getExpiry(), isSecure, isHttpOnly);
        }
    }

    public static String getCookieData(WebDriver driver, String nameOfCookie, CookieFunction<String> action) {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().contains(nameOfCookie)) {
                String value = action.run(cookie);
                log.info("Cookie data is :{}", value);
                return value;
            }
        }
        throw new AssertionError("No such cookie: " + nameOfCookie);
    }

    public static Boolean getCookieFlag(WebDriver driver, String nameOfCookie, CookieFunction<Boolean> action) {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().contains(nameOfCookie)) {
                Boolean value = action.run(cookie);
                log.info("Cookie flag is :{}", value);
                return value;

            }
        }
        throw new AssertionError("No such cookie: " + nameOfCookie);
    }

    public static boolean doesCookieExist(WebDriver driver, String nameOfCookie) {
        return driver.manage().getCookieNamed(nameOfCookie) != null;
    }

    public static String getLanguageCookieValue(WebDriver driver) {
        return driver.manage().getCookieNamed(LANGUAGE_COOKIE).getValue();
    }

    public static Cookie getLanguageCookie(WebDriver driver) {
        return driver.manage().getCookieNamed(LANGUAGE_COOKIE);
    }

    public static void deleteCookieByName(WebDriver driver, String cookieName) {
        driver.manage().deleteCookieNamed(cookieName);
    }

    public static void resetTestCookie(WebDriver driver) {
        setTestCookie(TestCookieContent.builder().build(), driver);
    }

    public static void setTestCookie(TestCookieContent cookieContent, WebDriver driver) {
        Cookie testCookie = new Cookie(TEST_COOKIE, cookieContent.createJSONBody());
        driver.manage().addCookie(testCookie);
    }

    public static void setInitialCookies(WebDriver driver) {
        if (!doesCookieExist(driver, TEST_COOKIE)) {
            resetTestCookie(driver);
        }
        if (!doesCookieExist(driver, DEBUG_COOKIE)) {
            driver.manage().addCookie(new Cookie(DEBUG_COOKIE, "true"));
        }
        log.info("Initial cookies have been set.");
    }

}
