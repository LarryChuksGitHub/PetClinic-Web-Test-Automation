package com.petclinic.testcommon.pageobject.web.petclinic;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.petclinic.testcommon.framework.utils.constant.NumericConstants;
import com.petclinic.testcommon.model.petclinic.VetData;
import com.petclinic.testcommon.pageobject.web.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VeterinarianPage extends Page {

    private final By vetRows =
            By.cssSelector("#vets tbody tr");

    @FindBy(xpath = "//span[@class='fa fa-th-list']/following-sibling::span")
    private WebElement veterinarianButton;

    @FindBy(css =  "a[title='Next']")
    private WebElement nextButton;

    public VeterinarianPage(WebDriver driver) {
        super(driver);
    }



    @Override
    public final void waitUntilPageLoads() {
    }

    public List<VetData> getVetsOnCurrentPage() {
        HomePage homePage = new HomePage(driver);
        homePage.openHomePage().openVeterinarians();

        List<VetData> vets = new ArrayList<>();
        collectAllVet( vets);
        while(isElementDisplayedWithWait(nextButton, NumericConstants.NUMERIC_3)) {
            nextButton.click();
            collectAllVet( vets);
        }
        log.info("Veterinarians:  {} ",vets);
        return vets;
    }

    private void collectAllVet( List<VetData> vets){

        List<WebElement> rows =
                driver.findElements(vetRows);

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(By.tagName("td"));

            String name =
                    cells.get(0).getText().trim();

            List<WebElement> specializationElements =
                    cells.get(1).findElements(
                            By.tagName("span")
                    );

            List<String> specializations =
                    specializationElements.stream()
                            .map(element ->
                                    element.getText().trim())
                            .toList();

            VetData vet = VetData.getEmptyVetData().setName(name).setSpecialization(specializations);
            vets.add(vet);
        }

    }

}
