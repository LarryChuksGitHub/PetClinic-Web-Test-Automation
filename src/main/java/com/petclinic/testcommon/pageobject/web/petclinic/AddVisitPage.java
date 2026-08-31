package com.petclinic.testcommon.pageobject.web.petclinic;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.petclinic.testcommon.pageobject.web.Page;

public class AddVisitPage extends Page {

    @FindBy(id = "description")
    private WebElement description;

    @FindBy(xpath = "//button[normalize-space()='Add Visit']")
    private WebElement addVisitButton;

    @FindBy(xpath = "//th[normalize-space()='Description']/ancestor::table//tbody/tr/td[2]")
    private WebElement descriptionValue;


    public AddVisitPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() throws SQLException {
    }

    public void enterDescription(String visitDescription) {
        waitUntilVisible(description).sendKeys(visitDescription);
    }

    public void save() {
        waitUntilClickable(addVisitButton).click();
    }

    public void addVisit(String visitDescription) {
        enterDescription(visitDescription);
        save();
    }

    public String getVisitDescription() {
     return  waitUntilClickable(descriptionValue).getText();
    }

}