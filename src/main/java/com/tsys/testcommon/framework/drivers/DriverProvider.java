package com.tsys.testcommon.framework.drivers;

import static com.tsys.testcommon.config.Config.BROWSER;
import static com.tsys.testcommon.model.common.Browser.CHROME;
import static com.tsys.testcommon.model.common.Browser.FIREFOX;

import org.openqa.selenium.WebDriver;

import com.tsys.testcommon.config.Config;
import com.tsys.testcommon.framework.drivers.impl.AndroidWebDriverProvider;
import com.tsys.testcommon.framework.drivers.impl.ChromeWebDriverProvider;
import com.tsys.testcommon.framework.drivers.impl.FirefoxWebDriverProvider;
import com.tsys.testcommon.framework.drivers.impl.IosWebDriverProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DriverProvider {

    public static LocalWebDriverProvider getLocalProvider() {
        switch (Config.PLATFORM_NAME) {
            case ANDROID:
                return new AndroidWebDriverProvider();
            case IOS, IPAD:
                return new IosWebDriverProvider();
            case WINDOWS:
                if (BROWSER == CHROME) {
                    return new ChromeWebDriverProvider();
                } else if (BROWSER == FIREFOX) {
                    return new FirefoxWebDriverProvider();
                }
            default:
                throw new IllegalArgumentException(String.format("Platform %s does not support remote execution!", Config.PLATFORM_NAME));
        }
    }

    public static RemoteWebDriverProvider getRemoteProvider(String testMethod) {
        switch (Config.PLATFORM_NAME) {
            case ANDROID:
                return new AndroidWebDriverProvider(testMethod);
            case IOS, IPAD:
                return new IosWebDriverProvider(testMethod);
            case WINDOWS:
                if (BROWSER == CHROME) {
                    return new ChromeWebDriverProvider(testMethod);
                } else if (BROWSER == FIREFOX) {
                    return new FirefoxWebDriverProvider(testMethod);
                }
            default:
                throw new IllegalArgumentException(String.format("Platform %s does not support remote execution!", Config.PLATFORM_NAME));
        }
    }

    public static WebDriver getChromeWithCustomLanguage(String language) throws Exception {
        log.info("Config.BROWSER: '{}'", BROWSER);
        ChromeWebDriverProvider provider = new ChromeWebDriverProvider()
                .setCustomChromeOptions(options -> options.addArguments("--lang=" + language));
        switch (BROWSER) {
            case REMOTE_CHROME:
                return provider.startRemote();
            case CHROME:
            default:
                return provider.start();
        }

    }

    public static WebDriver getChromeWithIncognitoMode() throws Exception {
        ChromeWebDriverProvider provider = new ChromeWebDriverProvider()
                .setCustomChromeOptions(options -> options.addArguments("--incognito"));
        switch (BROWSER) {
            case REMOTE_CHROME:
                return provider.startRemote();
            case CHROME:
            default:
                return provider.start();
        }
    }

}
