package com.petclinic.testcommon.pageobject.web.petclinic;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.petclinic.testcommon.framework.utils.constant.NumericConstants;
import com.petclinic.testcommon.model.petclinic.PetOwnerData;
import com.petclinic.testcommon.pageobject.web.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PetInfoPage extends Page {


    @FindBy(xpath = "//th[normalize-space()='Name']/following-sibling::td")
    private WebElement petOwnerlastnameValue;

    @FindBy(xpath = "//th[normalize-space()='Address']/following-sibling::td")
    private WebElement addressValue;

    @FindBy(xpath = "//th[normalize-space()='City']/following-sibling::td")
    private WebElement cityValue;

    @FindBy(xpath = "//th[normalize-space()='Telephone']/following-sibling::td")
    private WebElement telephoneValue;

    @FindBy(xpath = "//button[normalize-space()='Find Owner']")
    private WebElement findOwnerButton;

    @FindBy(xpath = "//a[normalize-space()='Add Owner']")
    private WebElement addOwnerButton;

    @FindBy(xpath = "//a[normalize-space()='Add New Pet']")
    private WebElement addPetButton;

    @FindBy(xpath = "//a[normalize-space()='Edit Owner']")
    private WebElement editOwnerButton;

    @FindBy(xpath = "//a[normalize-space()='Add Visit']")
    private List<WebElement> addVisitButton;

    @FindBy(xpath = "//dt[normalize-space()='Type']")
    private List<WebElement> petTypeText;

    @FindBy(xpath = "/dt[normalize-space()='Type']/following-sibling::dd[1]")
    private List<WebElement> petTypeValue;



    @FindBy(css =  "a[title='Next']")
    private WebElement nextButton;

    public PetInfoPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public final void waitUntilPageLoads() {
    }

    public AddPetPage clickAddNewPetButton() {
        waitUntilClickable(addPetButton).click();
        return new AddPetPage(driver);
    }

    public void clickVisitButton() {
        waitUntilClickable(addVisitButton.get(0)).click();
    }

    public boolean isOwnerDisplayed(String lastName) {
        By owner =
                By.xpath(
                        "//*[normalize-space()='" +
                                lastName +
                                "']"
                );
        while (isElementDisplayedWithWait(nextButton, NumericConstants.NUMERIC_2) && driver.findElements(owner).isEmpty()) {
            nextButton.click();
        }
        return !driver.findElements(owner).isEmpty();
    }

    public void editOwner(PetOwnerData petOwner) {
        waitUntilClickable(editOwnerButton).click();
        AddOwnerPage addOwnerPage = new AddOwnerPage(driver);
        addOwnerPage.enterOwner(petOwner);
    }

    public int getNumberOfPets() {
        return waitUntilVisible(petTypeValue).size();
    }

    public List<WebElement> getPetTypes() {
        return waitUntilVisible(petTypeValue);
    }

    public boolean isPetTypeDisplayed() {
     return isElementDisplayedWithWait(petTypeValue.get(0), NumericConstants.NUMERIC_5);

    }

    public  boolean isPetOwnerCreated(PetOwnerData petOwner){
        return waitUntilVisible(petOwnerlastnameValue).getText().contains(petOwner.getLastName())
                && waitUntilVisible(petOwnerlastnameValue).getText().contains(petOwner.getFirstName())
                && waitUntilVisible(addressValue).getText().equals(petOwner.getAddress())
                && waitUntilVisible(cityValue).getText().equals(petOwner.getCity())
                && waitUntilVisible(telephoneValue).getText().equals(petOwner.getTelephone());
        }

    public boolean isPetOwnerNameDisplayed(String lastName) {
     return waitUntilVisible(petOwnerlastnameValue).getText().contains(lastName);
    }



}
