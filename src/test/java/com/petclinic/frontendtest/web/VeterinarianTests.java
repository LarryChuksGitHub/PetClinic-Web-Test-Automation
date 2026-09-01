package com.petclinic.frontendtest.web;


import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_REGRESSION_PETCLINIC;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import com.petclinic.testcommon.config.hooks.Hooks;
import com.petclinic.testcommon.framework.asserts.DippSoftAssertions;
import com.petclinic.testcommon.model.petclinic.VetData;
import com.petclinic.testcommon.pageobject.web.petclinic.HomePage;
import com.petclinic.testcommon.pageobject.web.petclinic.VeterinarianPage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VeterinarianTests extends Hooks {

    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    private void setUp() {
        homePage = new HomePage(getDriver());
        homePage.openHomePage();
        softAssertion = new DippSoftAssertions();
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-VET-001: All Tierärzte soll angezeigt werden")
    void shouldDisplayAllVeterinarians() {

        VeterinarianPage veterinarianPage = new VeterinarianPage(getDriver());

        log.info("Very veterinarians are on the page");
        List<VetData> vets = veterinarianPage.getVetsOnCurrentPage();

        log.info("Very veterinarians are shown {}", vets);
        assertFalse(
                vets.isEmpty(),
                "Mindestens ein Tierarzt muss angezeigt werden."
        );

        for (VetData vet : vets) {
            assertFalse(
                    vet.getName().isBlank(),
                    "Der Name des Tierarztes darf nicht leer sein.");

            assertNotNull(vet.getSpecialization(),"Specialization des Tierarztes darf nicht null.");
        }
        dippAssertions.assertGreaterThan(5,"All Veterinarians were not displayed",vets.size());

    }

}
