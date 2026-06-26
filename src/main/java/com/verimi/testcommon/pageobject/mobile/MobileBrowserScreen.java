package com.verimi.testcommon.pageobject.mobile;

import static com.verimi.testcommon.config.Config.isIOS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobileBrowserScreen extends MobileScreen {

    @AndroidFindBy(id = "com.android.chrome:id/url_bar")
    @iOSXCUITFindBy(accessibility = "TabBarItemTitle")
    private WebElement urlBar;

    @AndroidFindBy(id = "com.android.chrome:id/line_2")
    @iOSXCUITFindBy(accessibility = "URL")
    private WebElement urlText;

    @iOSXCUITFindBy(accessibility = "ReloadButton")
    private WebElement reloadButton;

    public MobileBrowserScreen(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() {
        waitUntilVisible(urlBar);
    }

    public void openDeeplinkIos(String url) {
        if (isIOS()) {
                url = url.replaceAll("^https", "verimi");
            log.info("Deeplink Url: {}", url);
            if (!isElementDisplayed(goKeyboard)) {
                waitUntilClickable(urlBar).click();
            }
            new Actions(driver)
                    .sendKeys(url)
                    .perform();
            goKeyboard.click();
        }
    }
}
