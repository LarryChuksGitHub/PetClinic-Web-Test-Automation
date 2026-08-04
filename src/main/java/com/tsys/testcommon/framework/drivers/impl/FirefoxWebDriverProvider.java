package com.tsys.testcommon.framework.drivers.impl;

import java.time.Duration;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tsys.testcommon.framework.drivers.BaseRemoteWebDriverProvider;
import com.tsys.testcommon.framework.drivers.LocalWebDriverProvider;
import com.tsys.testcommon.framework.drivers.RemoteWebDriverProvider;
import com.tsys.testcommon.framework.utils.timeout.Timeout;

public class FirefoxWebDriverProvider extends BaseRemoteWebDriverProvider implements LocalWebDriverProvider, RemoteWebDriverProvider {

    public FirefoxWebDriverProvider() {
        super(null);
    }

    public FirefoxWebDriverProvider(String methodName) {
        super(methodName);
    }

    @Override
    public RemoteWebDriver start() {
        RemoteWebDriver driver = new FirefoxDriver(getOptions());
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Timeout.DEFAULT_PAGE_LOAD_TIME));
        return driver;
    }

    @Override
    protected String getBrowser() {
        return "firefox";
    }

    @Override
    protected DesiredCapabilities getCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getOptions());
        applyCustomCapabilities(desiredCapabilities);
        return desiredCapabilities;
    }

    protected FirefoxOptions getOptions(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("window-size=1920,1080");
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;

    }

/*
    @Override
    public RemoteWebDriver startRemoteForMobileWebApps() throws Exception {
        return null;
    }
*/
}
