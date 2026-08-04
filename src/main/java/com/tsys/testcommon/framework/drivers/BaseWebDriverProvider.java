package com.tsys.testcommon.framework.drivers;

import java.util.function.Consumer;

import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class BaseWebDriverProvider {

    private Consumer<DesiredCapabilities> action;

    public void setCustomCapabilities(Consumer<DesiredCapabilities> action) {
        this.action = action;
    }

    protected void applyCustomCapabilities(DesiredCapabilities desiredCapabilities) {
        if (action != null) {
            action.accept(desiredCapabilities);
        }
    }

}
