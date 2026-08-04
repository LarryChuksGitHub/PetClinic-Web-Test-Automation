package com.tsys.testcommon.config.cloudprovider;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.tsys.testcommon.model.common.SwipeDirection;

public abstract class CloudSpecificAction {

    protected final WebDriver driver;

    protected CloudSpecificAction(WebDriver driver) {
        this.driver = driver;
    }

    public static CloudSpecificAction getInstance(WebDriver driver) {

        return new BrowserstackAction(driver);
    }

    public abstract void swipe(SwipeDirection swipeDirection, Duration duration);
}
