package com.petclinic.testcommon.pageobject.web.petclinic;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.petclinic.testcommon.model.petclinic.PetData;
import com.petclinic.testcommon.pageobject.web.Page;

public class AddPetPage extends Page {

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "birthDate")
    private WebElement birthDate;

    @FindBy(id = "type")
    private WebElement type;

    @FindBy(xpath = "//button[normalize-space()='Add Pet']")
    private WebElement addPetButton;

    public AddPetPage(WebDriver driver) {
        super(driver);
    }

    public void enterPet(PetData pet) {
        type(name, pet.getName());
        type(birthDate, pet.getDateOfBirth());
        selectDropDownValueByText(type, pet.getPetType().getType());
    }

    public void save() {
        waitUntilClickable(addPetButton).click();
    }

    public void addPet(PetData pet) {
        enterPet(pet);
        save();
    }

    @Override
    public void waitUntilPageLoads() throws SQLException {

    }

}