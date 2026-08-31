package com.petclinic.testcommon.pageobject.web.petclinic;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.petclinic.testcommon.pageobject.web.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OwnerSearchPage extends Page {


    @FindBy(id = "lastName")
    private WebElement ownerLastNameTextField;

    @FindBy(xpath = "//button[normalize-space()='Find Owner']")
    private WebElement findOwnerButton;

    @FindBy(xpath ="//a[normalize-space()='Add Owner']")
    private WebElement addOwnerButton;

    public OwnerSearchPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public final void waitUntilPageLoads() {
    }


    public OwnerInfoPage searchOwner(String lastName) {
        enterLastName(lastName);
        waitUntilClickable(findOwnerButton).click();
        return new OwnerInfoPage(driver);
    }

    public void enterLastName(String lastName) {
        waitUntilClickable(ownerLastNameTextField);
        ownerLastNameTextField.clear();
        ownerLastNameTextField.sendKeys(lastName);
    }

    public AddOwnerPage clickAddOwner() {
        waitUntilClickable(addOwnerButton).click();
        return new AddOwnerPage(driver);
    }

    public boolean isOwnerDisplayed(String lastName) {
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(driver);
        return ownerInfoPage.isOwnerDisplayed(lastName);
    }
}
