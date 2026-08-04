package com.verimi.testcommon.model.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Browser {
    FIREFOX, CHROME, REMOTE_CHROME, CHROME_ANDROID, SAFARI_IOS;

    public static Browser parse(String stringToBrowser) {
        for (Browser browser : Browser.values()) {
            if (browser.name().equalsIgnoreCase(stringToBrowser)) {
                return browser;
            }
        }
        log.warn("Unknown browser '{}'. Setting to default Chrome", stringToBrowser);
        return CHROME;
    }
}
