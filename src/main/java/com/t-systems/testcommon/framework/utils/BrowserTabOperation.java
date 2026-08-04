package com.verimi.testcommon.framework.utils;

import java.time.Duration;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.verimi.testcommon.framework.utils.timeout.Timeout;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserTabOperation {

    public WebDriver driver;

    public BrowserTabOperation(WebDriver driver) {
        this.driver = driver;
    }

    public void switchBrowserTab(String currentWindowHandle) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        allWindowHandles.forEach(window ->
                {
                    if (!window.equals(currentWindowHandle)) {
                        driver.switchTo().window(window);
                    }
                }
        );
    }

    public boolean checkTabsNumberAndUrl(int tabNumbers, String url) {
        new WebDriverWait(driver, Duration.ofSeconds(Timeout.DEFAULT_ELEMENT_TIMEOUT)).until(ExpectedConditions.numberOfWindowsToBe(tabNumbers));
        String currentURL = driver.getCurrentUrl();
        log.info("browser URL: {}", currentURL);
        return currentURL.equals(url);
    }

    public void waitForExpectedTabCount(int expectedTabCount) {
        OptionalInt optionalInt = IntStream.rangeClosed(1, 10).filter(i -> driver.getWindowHandles().size() == expectedTabCount).findFirst();
        if (!optionalInt.isPresent()) {
            throw new IllegalStateException(String.format("Unable to open expected number of tabs: %s", expectedTabCount));
        }
    }
}