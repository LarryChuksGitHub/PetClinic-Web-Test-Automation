package com.petclinic.frontendtest.web;


import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_REGRESSION_PETCLINIC;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Tag(name = "major")
    @DisplayName("TC-VET-001: All Tierärzte soll angezeigt werden")
    void shouldDisplayAllVeterinarians() {

        VeterinarianPage veterinarianPage = new VeterinarianPage(getDriver());

        log.info("Very veterinarians and specialization are on the page");
        List<VetData> vets = veterinarianPage.getVetsOnCurrentPage();

        log.info("Very veterinarians are unique");
        Set<String> vetName = vets.stream().map(VetData::getName).collect(Collectors.toSet());

        // We assume the number of vets can only increase
        Set<List<String>> vetSpecialization = vets.stream().map(VetData::getSpecialization).collect(Collectors.toSet());
        dippAssertions.assertGreaterThan(5,"All Veterinarians were not displayed",vetName.size());

        // We assume the number of specializations can only increase
        dippAssertions.assertGreaterThan(3,"All Veterinarians were not displayed",vetSpecialization.size());

        List<String> listOfVets =  List.of("Linda Douglas", "Rafael Ortega", "Henry Stevens", "Sharon Jenkins", "James Carter", "Helen Leary");

        for (VetData vet : vets) {
            assertFalse(
                    vet.getName().isBlank(),
                    "Name of vet is not blank.");

            // We assume that vet names and their specialization will not change and number of vets can only increase
            assertTrue(listOfVets.contains(vet.getName()),
                    "The list of vets is not complete.");

            assertNotNull(vet.getSpecialization(),"Specialization of vet should not be empty.");

            log.info("Verify vets are correctly mapped to their specialization");
            if(vet.getName().equals("James Carter")){
                assertEquals(vet.getSpecialization().iterator().next(), "none", "Vet is not mapped to the correct specialization.");
            } else if(vet.getName().equals("Helen Leary")){
                assertEquals(vet.getSpecialization().iterator().next(), "radiology", "Vet is not mapped to the correct specialization.");
            } else if (vet.getName().equals("Linda Douglas")) {
                assertEquals(vet.getSpecialization(), List.of("dentistry" ,"surgery"), "Vet is not mapped to the correct specialization.");

            } else if (vet.getName().equals("Rafael Ortega")) {
                assertEquals(vet.getSpecialization().iterator().next(), "surgery", "Vet is not mapped to the correct specialization.");

            } else if (vet.getName().equals("Henry Stevens")) {
                assertEquals(vet.getSpecialization().iterator().next(), "radiology", "Vet is not mapped to the correct specialization.");

            }
            else if (vet.getName().equals("Sharon Jenkins")) {
                assertEquals(vet.getSpecialization().iterator().next(), "none", "Vet is not mapped to the correct specialization.");

            }

            // We assume that specialization will not change and number of specialization can only increase
            List<String> listOfSpecialization =  List.of("none", "radiology", "dentistry", "surgery");
            assertTrue(listOfSpecialization.contains(vet.getSpecialization().iterator().next()),
                    "The list of vets is not complete.");
        }
        dippAssertions.assertGreaterThan(5,"All Veterinarians were not displayed",vets.size());

    }

}
