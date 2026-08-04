package com.tsys.testcommon.flows;

import org.openqa.selenium.WebDriver;

import com.tsys.testcommon.framework.drivers.DriverManager;

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

    public static String TWO_FA_PIN = "123455";
    public static String EID_PIN = "123456";
    public static String PHONE_NUMBER = "17681066891";
    public static String POSTCODE = "12279";

    /**
     * Use getter getDriver()
     * YOU MUST NOT TO ACCESS FIELD DIRECTLY!
     */
    private WebDriver driver;

    public BasicFlow(WebDriver driver) {
        this.driver = driver;
    }


    protected WebDriver getDriver() {
        if (driver == null) {
            driver = DriverManager.getDriver();
        }
        return driver;
    }

    public void openPage(String url) {
        getDriver().get(url);
    }

}
