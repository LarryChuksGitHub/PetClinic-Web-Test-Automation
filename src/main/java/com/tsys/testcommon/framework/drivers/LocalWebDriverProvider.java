package com.tsys.testcommon.framework.drivers;

import org.json.JSONException;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface LocalWebDriverProvider {

    RemoteWebDriver start() throws JSONException;
}
