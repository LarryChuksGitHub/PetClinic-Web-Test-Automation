package com.petclinic.frontendtest.web;


import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_SMOKE_PETCLINIC;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import com.petclinic.testcommon.config.hooks.Hooks;
import com.petclinic.testcommon.flows.PetClinicFlow;
import com.petclinic.testcommon.framework.utils.constant.NumericConstants;
import com.petclinic.testcommon.framework.utils.random.RandomUtilities;
import com.petclinic.testcommon.framework.utils.retry.RetryUtils;
import com.petclinic.testcommon.model.petclinic.PetData;
import com.petclinic.testcommon.model.petclinic.PetOwnerData;
import com.petclinic.testcommon.model.petclinic.PetType;
import com.petclinic.testcommon.pageobject.web.petclinic.OwnerInfoPage;
import com.petclinic.testcommon.testdata.petclinic.TestDataFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EndToEndTests extends Hooks {

    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-INT-001: Kompletter Prozess Owner -> PetData -> VisitData")
    void shouldCreateOwnerPetAndVisit() {
        PetOwnerData owner = TestDataFactory.generateUniquePetOwnerData();

        // Owner anlegen
        createOwner(owner);

        // Assertion
        assertOwnerExists(owner);

        // PetData anlegen
        String petName = RandomUtilities.generateRandomName();
        PetData pet = PetData.getEmptyPetData().setName(petName).setDateOfBirth("01.02.2025").setPetType(PetType.CAT);
        addPet(owner, pet);

        log.info("Ensure Pet is added");
        assertPetExists(pet.getName());

        String visitType = "Leg treatment";
        // VisitData anlegen
        createVisit(owner, 1, visitType);

        log.info("Ensure Visit is added");
        assertVisitExists(visitType);
    }


    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-INT-002: Kompletter Prozess Owner -> PetData und Visits sind getrennt")
    void shouldKeepPetsAndVisitsIndependent() {
        PetOwnerData owner = TestDataFactory.generateUniquePetOwnerData();

        // Owner anlegen
        createOwner(owner);

        // PetData anlegen
        String petName1 = RandomUtilities.generateRandomName();
        String petName2 = RandomUtilities.generateRandomName();
        PetData pet1 = PetData.getEmptyPetData().setName(petName1).setDateOfBirth("01.02.2025").setPetType(PetType.CAT);
        PetData pet2 = PetData.getEmptyPetData().setName(petName2).setDateOfBirth("01.02.2025").setPetType(PetType.CAT);

        addPet(owner, pet1);
        addPet(owner, pet2);


        String visitType1 = "Leg treatment";

        String visitType2 = "Teeth Check up";
        // VisitData anlegen
        createVisit(owner, 1, visitType1);

        createVisit(owner, 2, visitType2);

        // Assert
        // PetData 1 darf nur seinen eigenen VisitData haben
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        String visitDescription = ownerInfoPage.openVisit(0).getVisitDescription();

        assertTrue(visitDescription.contains(visitType1),
                "VisitData 1 should only shown for pet 1"
        );

        // PetData 2 darf nicht VisitData 1 haben
        assertFalse(visitDescription.contains(visitType2),
                "VisitData 2 should not be shown for pet 1"
        );


        // PetData B darf nur seinen eigenen VisitData haben
        ownerInfoPage = new OwnerInfoPage(getDriver());
        getDriver().navigate().back();
        visitDescription = ownerInfoPage.openVisit(1).getVisitDescription();

        assertTrue(visitDescription.contains(visitType2),
                "VisitData 2 muss bei PetData 2 angezeigt werden."
        );

        // PetData B darf nicht VisitData 1 haben
        assertFalse(visitDescription.contains(visitType1),
                "VisitData 1 darf bei PetData 2 nicht angezeigt werden."
        );
    }

    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-INT-003: Gleicher PetData-Name bei unterschiedlichen Ownern")
    void shouldSupportSamePetNameForDifferentOwners() {
        //Owners anlegen
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        List<PetOwnerData> petOwers = petClinicFlow.createMultiplePetOwner(3);

        // Data for 1 pet anlegen
        String petName = RandomUtilities.generateRandomName();
        PetData pet = PetData.getEmptyPetData().setName(petName).setDateOfBirth("01.02.2025").setPetType(PetType.CAT);

        // Add same pet name to 1 Owner und validieren
        petClinicFlow.searchPetOwner(petOwers.get(0).getLastName());
        addPet(petOwers.get(0), pet);
        assertPetExists(petName);

        // Add same pet name to 2 Owner und validieren
        petClinicFlow.searchPetOwner(petOwers.get(1).getLastName());
        addPet(petOwers.get(1), pet);
        assertPetExists(petName);

        // Add same pet name to 3 Owner und validieren
        petClinicFlow.searchPetOwner(petOwers.get(2).getLastName());
        addPet(petOwers.get(2), pet);
        assertPetExists(petName);
    }

    private PetOwnerData createOwner(PetOwnerData owner) {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
       return petClinicFlow.createNewPetOwner(owner);
    }

    private void assertOwnerExists(PetOwnerData owner) {
        assertSearchResult(owner.getLastName(), "PetData owner with name" + owner.getLastName()+ " should exist");

    }

    private void addPet(PetOwnerData peterOwner , PetData pet) {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.createNewPet(peterOwner, pet);
    }

    private void assertPetExists(String petName) {
        assertSearchResult(petName, "PetData Name" + petName+ " should exist");
    }

    private void createVisit(PetOwnerData owner, int petId, String description) {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.addPetClinicVisit(owner,petId,description);

    }

    private void assertVisitExists(String description) {
        assertSearchResult(description, "VisitData type should be shown");
    }

    private void assertSearchResult(String expectedText, String message) {
        assertTrue(
                RetryUtils.isConditionFulfilledWithWait(()-> getDriver().getPageSource().contains(expectedText), NumericConstants.NUMERIC_8),
                message
        );

    }

}
