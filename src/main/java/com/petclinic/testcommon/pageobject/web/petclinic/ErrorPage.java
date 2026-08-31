package com.petclinic.testcommon.pageobject.web.petclinic;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.petclinic.testcommon.pageobject.web.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorPage extends Page {


    @FindBy(xpath = "//button[normalize-space()='Find Owner']")
    private WebElement findOwnerButton;

    @FindBy(xpath ="//a[normalize-space()='Add Owner']")
    private WebElement addOwnerButton;

    protected ErrorPage(WebDriver driver) {
        super(driver);
    }



    @Override
    public final void waitUntilPageLoads() {
    }
    public boolean isOwnerDisplayed(String lastName) {
        By owner =
                By.xpath(
                        "//*[normalize-space()='" +
                                lastName +
                                "']"
                );
        return !driver.findElements(owner).isEmpty();
    }
}
