package com.verimi.testcommon.framework.utils.testhelper;

import org.openqa.selenium.WebDriver;

/**
 * Base class for flow.
 * Restricts usage of driver field and instead offers getDriver() method
 * that allows lazy init of WebDriver whenever it is needed
 * <p>
 * Inherited class must implement at least one of constructors that accepts:
 * * WebDriver - old approach when driver instance is already created
 * * DriverReference - new approach when we create instance of WebDriver lazily on first actual call of getDriver() method
 */
public abstract class BasicFlow {
    /**
     * Use getter getDriver()
     * YOU MUST NOT TO ACCESS FIELD DIRECTLY!
     */
    private WebDriver driver;
    private DriverReference getDriverReference;

    public BasicFlow(WebDriver driver) {
        this.driver = driver;
    }

    public BasicFlow(DriverReference getDriverReference) {
        this.getDriverReference = getDriverReference;
    }

    protected WebDriver getDriver() {
        if (driver == null) {
            if (getDriverReference != null) {
                driver = getDriverReference.call();
            }
        }
        return driver;
    }

    public void openPage(String url) {
        getDriver().get(url);
    }

    @FunctionalInterface
    public interface DriverReference {
        WebDriver call();
    }

}
