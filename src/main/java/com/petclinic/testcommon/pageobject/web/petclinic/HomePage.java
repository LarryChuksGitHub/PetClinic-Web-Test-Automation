package com.petclinic.testcommon.pageobject.web.petclinic;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.petclinic.testcommon.pageobject.web.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomePage extends Page {
    public static final String BASE_URL = "http://localhost:8080/";

    @FindBy(css =  "a[title='home page']")
    private WebElement homePageButton;


    @FindBy(xpath =   "//a[@href='/owners/find']")
    private WebElement findOwner;

    @FindBy(xpath = "//a[@title='veterinarians']")
    private WebElement veterinarians;

    @FindBy(xpath = "//span[@class='fa fa-th-list']/following-sibling::span")
    private WebElement veterinarianButton;

    @FindBy(xpath =   "//a[@href='/oups']")
    private WebElement error;



    public HomePage(WebDriver driver) {
        super(driver, "");
        waitUntilPageLoads();
    }

    public HomePage openHomePage() {
        driver.get(BASE_URL);
        waitUntilVisible(homePageButton);
        return this;
    }

    public OwnerSearchPage openFindOwner() {
        waitUntilClickable(findOwner).click();
        return new OwnerSearchPage(driver);
    }

     public VeterinarianPage openVeterinarians() {
        waitUntilClickable(veterinarians).click();
        return new VeterinarianPage(driver);
    }
 public ErrorPage openErrorPage() {
        waitUntilClickable(error).click();
        return new ErrorPage(driver);
    }


    @Override
    public final void waitUntilPageLoads() {
    }
}
