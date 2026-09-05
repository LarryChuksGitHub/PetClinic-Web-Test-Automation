package com.petclinic.frontendtest.web;


import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_REGRESSION_PETCLINIC;
import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_SMOKE_PETCLINIC;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import com.github.javafaker.Faker;
import com.petclinic.testcommon.config.hooks.Hooks;
import com.petclinic.testcommon.flows.PetClinicFlow;
import com.petclinic.testcommon.framework.asserts.DippSoftAssertions;
import com.petclinic.testcommon.framework.utils.constant.NumericConstants;
import com.petclinic.testcommon.framework.utils.datetime.DateTimeUtils;
import com.petclinic.testcommon.framework.utils.random.RandomUtilities;
import com.petclinic.testcommon.framework.utils.retry.RetryUtils;
import com.petclinic.testcommon.model.petclinic.PetData;
import com.petclinic.testcommon.model.petclinic.PetOwnerData;
import com.petclinic.testcommon.model.petclinic.PetType;
import com.petclinic.testcommon.pageobject.web.petclinic.AddPetPage;
import com.petclinic.testcommon.pageobject.web.petclinic.HomePage;
import com.petclinic.testcommon.pageobject.web.petclinic.OwnerInfoPage;
import com.petclinic.testcommon.testdata.petclinic.TestDataFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PetTests extends Hooks {

    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    private void setUp() {
        homePage = new HomePage(getDriver());
        homePage.openHomePage();
        softAssertion = new DippSoftAssertions();
    }

    @Tag(name = "major")
    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @DisplayName("TC-PET-001: Neues PetData kann angelegt werden")
    void shouldAddNewPet() {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.createNewPetOwner();
        String id = RandomUtilities.generateRandomUID().substring(0, 10);
        PetData pet = PetData.getEmptyPetData().setName("Bello" + id).setDateOfBirth("10.10.2020").setPetType(PetType.LIZARD);

        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton().addPet(pet);

        log.info("Verify Pet is added");
        assertTrue(
                RetryUtils.isConditionFulfilledWithWait(() -> getDriver().getPageSource().contains(pet.getName()), NumericConstants.NUMERIC_8),
                "PetData Name should be created with name " + pet.getName()
        );
    }


    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-PET-009: Alle Pet typen kann angelegt werden")
    void shouldAddAllPetTypes() {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.createNewPetOwner();
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());

        log.info("Generate list of pets types");
        List<PetType> pets = Arrays.stream(PetType.values()).toList();
        for (PetType petType : pets) {
            String id = RandomUtilities.generateRandomUID().substring(0, 10);
            PetData pet = PetData.getEmptyPetData().setName("Bello" + id).setDateOfBirth(DateTimeUtils.getPastDateInDdMmYyyy(-15)).setPetType(petType);
            ownerInfoPage.clickAddNewPetButton().addPet(pet);
            assertPetExist(pet.getName());
        }

        //Get list of created pet types
        List<String> petTypes = ownerInfoPage.getPetTypes();
        Set<String> petTypesSet = Set.copyOf(petTypes);

        log.info("Verify different Pet were added");
        assertEquals(petTypes.size(), pets.size(), "Different pets were not added");
        dippAssertions.assertGreaterThan(5, "Different pet types should be added", petTypesSet.size());
    }

    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-PET-009: Alle PetData typen kann angelegt werden")
    void shouldAddMultiplePetsOfDifferentType() {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        int numberOfPets = 30;
        PetOwnerData owner = TestDataFactory.generateUniquePetOwnerData();

        log.info("Add multiple pets types");
        petClinicFlow.addDifferentPets(owner,numberOfPets);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
                assertEquals(ownerInfoPage.getNumberOfPets(), numberOfPets, "Number of Pets added is not correct, expected amount " + numberOfPets);

        // Check for different pet types
        List<String> differentPetTypes = ownerInfoPage.getPetTypes();
        Set<String> petType = Set.copyOf(differentPetTypes);

        log.info("Verify different Pet types were added");
        assertTrue(petType.size() > 3,
                "Different pet types were not added");
    }

    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-PET-003: Derselbe Owner darf kein PetData mit gleichem Namen anlegen")
    void shouldRejectDuplicatePetNameForSameOwner() {
        String petName = Faker.instance().name().firstName();
        PetData pet = PetData.getEmptyPetData().setName(petName).setDateOfBirth("2020-05-10").setPetType(PetType.LIZARD);

        AddPetPage addPetPage = createOwnerAndPreparePetFlow();

         // Add first pet
        addPetPage.addPet(pet);
        assertPetExist(petName);

        // Add Second pet
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
        addPetPage.addPet(pet.setPetType(PetType.DOG));

        log.info("Verify Pet with same name was not added");
        assertTrue(
                RetryUtils.isConditionFulfilledWithWait(() -> Objects.requireNonNull(getDriver().getPageSource()).contains("is already in use"), NumericConstants.NUMERIC_8),
                "PetData Name must be unique for same owner pet, duplicate pet name " + petName
        );
    }

    private void assertPetExist(String petName) {
            assertTrue(
                    RetryUtils.isConditionFulfilledWithWait(()-> getDriver().getPageSource().contains(petName), NumericConstants.NUMERIC_8),
                    "Pet with name " + petName + " was not found"
            );
    }

    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-PET-003B: Gleicher PetData-Name ist bei anderem Owner erlaubt")
    void shouldAllowSamePetNameForDifferentOwner() {
        String id = RandomUtilities.generateRandomUID().substring(0, 10);
        String petName = Faker.instance().name().firstName() + id;
        PetData pet = PetData.getEmptyPetData().setName(petName).setDateOfBirth("10.10.2020").setPetType(PetType.HAMSTER);

        AddPetPage addPetPage  = createOwnerAndPreparePetFlow();
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());

        addPetPage.addPet(pet);
        RetryUtils.isConditionFulfilledWithWait(() -> Objects.requireNonNull(getDriver().getPageSource()).contains(pet.getName()), NumericConstants.NUMERIC_8);

        log.info("Create 2 owner");
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.createNewPetOwner();

        log.info("Add same pet name " + petName);
        ownerInfoPage.clickAddNewPetButton();
        addPetPage.addPet(pet);

        assertPetExist(petName);

        log.info("Verify Pet with same name was added");
        assertFalse(
                RetryUtils.isConditionFulfilledWithWait(() -> Objects.requireNonNull(getDriver().getPageSource()).contains("is already in use"), NumericConstants.NUMERIC_2),
                "Same PetData Name can exist for different users " + petName
        );
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @DisplayName("TC-PET-004: PetData ohne Namen oder Geburtstag kann nicht angelegt werden")
    void shouldNotCreatePetWithoutNameAndDateOfBirth() {

        PetData snake = PetData.getEmptyPetData().setName("").setDateOfBirth(DateTimeUtils.getPastDateInDdMmYyyy(-6)).setPetType(PetType.SNAKE);
        AddPetPage addPetPage = createOwnerAndPreparePetFlow();
        addPetPage.addPet(snake);
        addPetPage.save();
        assertTrue(
                getDriver().getCurrentUrl()
                        .contains("/pets/new")
        );

        snake.setName(RandomUtilities.generateRandomName()+RandomUtilities.generateRandomUID().substring(0, 10));
        snake.setDateOfBirth("");
        addPetPage.addPet(snake);
        addPetPage.save();

        log.info("Very pet was not added ");
        assertTrue(
                getDriver().getCurrentUrl()
                        .contains("/pets/new")
        );
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-PET-005: PetData mit zukünftigem Geburtsdatum")
    void shouldHandleFutureBirthDate() {
        String petName = Faker.instance().name().firstName();
        PetData pet = PetData.getEmptyPetData().setName(petName).setDateOfBirth(DateTimeUtils.getFutureDateInDdMmYyyy(2)).setPetType(PetType.DOG);
        AddPetPage addPetPage = createOwnerAndPreparePetFlow();
        addPetPage.addPet(pet);

        log.info("Very pet wit wrong date was not added ");
        assertTrue(
                RetryUtils.isConditionFulfilledWithWait(() -> Objects.requireNonNull(getDriver().getPageSource()).contains("invalid date"), NumericConstants.NUMERIC_3),
                "Future date are not allowed"
        );
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-PET-007: Existierende Pet erfolgreich bearbeiten")
    void shouldEditPet() {
        PetOwnerData petOwnerData = TestDataFactory.generateUniquePetOwnerData();
        PetData snake = TestDataFactory.uniquePet(PetType.SNAKE);
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.createNewOwnerWithPet(petOwnerData, snake);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        PetData bird = TestDataFactory.uniquePet(PetType.BIRD);
        ownerInfoPage.editPet(bird);

        log.info("Very pet data was edited");
        assertPetExist(bird.getName());
    }

    private AddPetPage createOwnerAndPreparePetFlow(){
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.createNewPetOwner();
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
        return new AddPetPage(getDriver());
    }
}
