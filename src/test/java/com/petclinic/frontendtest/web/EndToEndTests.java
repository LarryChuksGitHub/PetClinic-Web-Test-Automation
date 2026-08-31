package com.petclinic.frontendtest.web;


import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_SMOKE_PETCLINIC;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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


public class EndToEndTests extends Hooks {

    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-INT-001: Kompletter Prozess Owner -> PetData -> VisitData")
    void shouldCreateOwnerPetAndVisit() {
        PetOwnerData owner = TestDataFactory.uniqueOwner();

        // Owner anlegen
        createOwner(owner);

        // Assertion
        assertOwnerExists(owner);

        // PetData anlegen
        String petName = RandomUtilities.generateRandomName();
        PetData pet = PetData.getEmptyPetData().setName(petName).setDateOfBirth("01.02.2025").setPetType(PetType.CAT);
        addPet(owner, pet);

        assertPetExists(pet.getName());

        String visitType = "Leg treatment";
        // VisitData anlegen
        createVisit(owner, 1, visitType);

        assertVisitExists(visitType);
    }


    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-INT-002: Kompletter Prozess Owner -> PetData und Visits sind getrennt")
    void shouldKeepPetsAndVisitsIndependent() {
        PetOwnerData owner = TestDataFactory.uniqueOwner();

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
       // Generate Data for Owners
        PetOwnerData owner1 = TestDataFactory.uniqueOwner();
        PetOwnerData owner2 = TestDataFactory.uniqueOwner();
        PetOwnerData owner3 = TestDataFactory.uniqueOwner();

        // 1. Owner anlegen
        PetOwnerData petOwner1 = createOwner(owner1);

        PetOwnerData petOwner2 = createOwner(owner2);

        PetOwnerData petOwner3 = createOwner(owner3);

        // 2. PetData anlegen
        String petName = RandomUtilities.generateRandomName();
        PetData pet = PetData.getEmptyPetData().setName(petName).setDateOfBirth("01.02.2025").setPetType(PetType.CAT);
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());

        // Add same pet name to 1 Owner
        petClinicFlow.searchPetOwner(petOwner1.getLastName());
        addPet(owner1, pet);
        assertPetExists(petName);

        // Add same pet name to 2 Owner
        petClinicFlow.searchPetOwner(petOwner2.getLastName());
        addPet(owner2, pet);
        assertPetExists(petName);

        // Add same pet name to 3 Owner
        petClinicFlow.searchPetOwner(petOwner3.getLastName());
        addPet(owner3, pet);
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
