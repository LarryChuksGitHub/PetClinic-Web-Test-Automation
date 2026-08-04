package com.tsys.testcommon.framework.drivers;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface RemoteWebDriverProvider {

    RemoteWebDriver startRemote() throws Exception;
    /*RemoteWebDriver startRemoteForMobileWebApps() throws Exception;*/
}
