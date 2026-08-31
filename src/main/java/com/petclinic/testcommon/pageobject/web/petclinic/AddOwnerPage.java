package com.petclinic.testcommon.pageobject.web.petclinic;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.petclinic.testcommon.model.petclinic.PetOwnerData;
import com.petclinic.testcommon.pageobject.web.Page;

public class AddOwnerPage extends Page {


    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "telephone")
    private WebElement telephone;

    private final By addOwnerButton =
            By.xpath("//button[normalize-space()='Add Owner']");

    @FindBy(xpath = " //div[@class='form-group has-error']")
    private List<WebElement> errorMessages;

    public AddOwnerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageLoads() throws SQLException {

    }

    public void enterOwner(PetOwnerData petOwner) {
       type(firstName, petOwner.getFirstName());

        type(lastName, petOwner.getLastName());

        type(address, petOwner.getAddress());

        type(city, petOwner.getCity());

        type(telephone, petOwner.getTelephone());

    }

    public void clickAddOwner() {
        waitUntilClickable(addOwnerButton).click();
    }

    public OwnerInfoPage createOwner(PetOwnerData owner) {
        enterOwner(owner);
        clickAddOwner();
        return new OwnerInfoPage(driver);
    }

    public boolean isValidationDisplayed(By field) {

        return driver.findElements(field).stream()
                .anyMatch(element ->
                        "true".equals(
                            element.getAttribute("aria-invalid")
                        )
                );
    }

    public int getNumberOfErrors() {
        return waitUntilVisible(errorMessages).size();
    }
}