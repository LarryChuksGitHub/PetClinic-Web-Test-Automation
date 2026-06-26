package com.verimi.testcommon.framework.utils.conditions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.functions.ExpectedCondition;

public class CustomExpectedConditions {

    public static ExpectedCondition<Boolean> visibilityOfAnyElement(By by1, By by2) {
        return driver -> {
            WebElement element1 = null;
            WebElement element2 = null;
            try {
                element1 = ExpectedConditions.visibilityOfElementLocated(by1).apply(driver);
            } catch (WebDriverException wde) {}
            try {
                element2 = ExpectedConditions.visibilityOfElementLocated(by2).apply(driver);
            } catch (WebDriverException wde) {}
            return element1 != null && element1.isDisplayed() ||
                    element2 != null && element2.isDisplayed();
        };
    }
}
