package com.petclinic.testcommon.flows;

import static com.petclinic.testcommon.framework.utils.random.RandomUtilities.generateRandomName;
import static com.petclinic.testcommon.pageobject.web.petclinic.HomePage.BASE_URL;
import static com.petclinic.testcommon.testdata.petclinic.TestDataFactory.generateRandomPetDateOfBirth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.petclinic.testcommon.framework.utils.random.RandomUtilities;
import com.petclinic.testcommon.model.petclinic.PetData;
import com.petclinic.testcommon.model.petclinic.PetOwnerData;
import com.petclinic.testcommon.model.petclinic.PetType;
import com.petclinic.testcommon.pageobject.web.petclinic.AddOwnerPage;
import com.petclinic.testcommon.pageobject.web.petclinic.AddPetPage;
import com.petclinic.testcommon.pageobject.web.petclinic.AddVisitPage;
import com.petclinic.testcommon.pageobject.web.petclinic.HomePage;
import com.petclinic.testcommon.pageobject.web.petclinic.OwnerInfoPage;
import com.petclinic.testcommon.pageobject.web.petclinic.OwnerSearchPage;
import com.petclinic.testcommon.testdata.petclinic.TestDataFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PetClinicFlow extends BasicFlow {


    public PetClinicFlow(WebDriver driver) {
        super(driver);
    }

    public void openExistingOwner(String ownerId) {
        getDriver().get(
                BASE_URL +
                        "/owners/" + ownerId
        );
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
    }

    public void addNewPetFlow(String ownerId) {
        openExistingOwner(ownerId);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
    }

    public PetOwnerData createNewPetOwner() {
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();

        log.info("Generate data for new pet owner");
        PetOwnerData owner =
                TestDataFactory.generateUniquePetOwnerData();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();

        log.info("create new owner");
        addOwnerPage.createOwner(owner);
        return owner;
    }

    public PetOwnerData createNewPetOwner(PetOwnerData owner) {

        log.info("Navigate t owner search page");
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();

        log.info("create new owner");
        addOwnerPage.createOwner(owner);
        return owner;
    }

    public List<PetOwnerData> createNewPetOwners(PetOwnerData owner, boolean sameName, int numberOfOwner) {
        HomePage homePage = new HomePage(getDriver());
        List<PetOwnerData> owners = new ArrayList<>();
        while (numberOfOwner > 0) {

            log.info("Check if the owner should be same or different owners");
            if(!sameName) {

                log.info("Generate data for different pet owners");
                owner = TestDataFactory.generateUniquePetOwnerData();
            }
            OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
            AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();

            log.info("create new owner and add to the list");
            addOwnerPage.createOwner(owner);
            owners.add(owner);
            numberOfOwner--;
        }
        return owners;
    }


    public PetOwnerData editPetOwner(PetOwnerData petOwner) {

        log.info("Create owner and edit pet owner data");
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();
        PetOwnerData owner =
                TestDataFactory.generateUniquePetOwnerData();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.editOwner(petOwner);
        return petOwner;
    }

    public PetOwnerData createNewPet() {

        log.info("Create new pet");
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();
        PetOwnerData owner =
                TestDataFactory.generateUniquePetOwnerData();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();

        log.info("Create new owner");
        addOwnerPage.createOwner(owner);

        log.info("generate data for new pet");
        String id = RandomUtilities.generateRandomUID();
        PetData pet = PetData.getEmptyPetData().setName("Bello" + id).setDateOfBirth("2020-05-10").setPetType(PetType.DOG);
        AddPetPage addPetPage = new AddPetPage(getDriver());

        log.info("Add pet");
        addPetPage.addPet(pet);
        return owner;
    }

    public Map<PetOwnerData, List<PetData>> addDifferentPets(PetOwnerData owner, int numberOfPets) {

        log.info("Add multiple pets of different types to same owner");
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();

        log.info("Create new pet owner");
        addOwnerPage.createOwner(owner);

        log.info("Create list of pets types and pets");
        List<PetType> petTypes = Arrays.stream(PetType.values()).toList();
        List<PetData> pets = new ArrayList<>();

        log.info("Map of pets owners and pets");
        Map<PetOwnerData, List<PetData>> petOwnerAndPets = new HashMap<>();
        int j = 0;
        OwnerInfoPage ownerInfoPage;
        for (int i = 0; i < numberOfPets; i++) {
            String id = RandomUtilities.generateRandomUID().substring(0, 10);
            if (i > petTypes.size() - 1 && (i == j || i % petTypes.size() == 0)) {

                log.info("Create new list of pets types after exhausting the current list and set the index pointer to 0");
                petTypes = Arrays.stream(PetType.values()).toList();
                j = 0;
            }
            log.info("Create new pet data");
            PetData pet = PetData.getEmptyPetData().setName(generateRandomName() + id).setDateOfBirth(generateRandomPetDateOfBirth()).setPetType(petTypes.get(j));
            j++;
            ownerInfoPage = new OwnerInfoPage(getDriver());

            log.info("Add pet to owner and to list of pets");
            ownerInfoPage.clickAddNewPetButton().addPet(pet);
            pets.add(pet);
        }
        petOwnerAndPets.put(owner, pets);
        return petOwnerAndPets;
    }

    public Map<List<PetOwnerData>, List<PetData>> addDifferentPets(List<PetOwnerData> owners, int numberOfPets) {

        log.info("Add multiple pets of different types to multiple owner");
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();

        log.info("Create list of pets types and pets");
        List<PetType> petTypes = Arrays.stream(PetType.values()).toList();
        List<PetData> pets = new ArrayList<>();

        log.info("Map of list of pets owners and list of pets");
        Map<List<PetOwnerData>, List<PetData>> petOwnerAndPets = new LinkedHashMap<>();
        int j = 0;
        int petOwners = owners.size();
        for (int i = 0; i < numberOfPets; i++) {
            String id = RandomUtilities.generateRandomUID().substring(0, 10);
            if (i > petTypes.size() - 1 && (i == j || i % petTypes.size() == 0)) {

                log.info("Create new list of pets types after exhausting the current list and set the index pointer to 0");
                petTypes = Arrays.stream(PetType.values()).toList();
                j = 0;
            }

            log.info("Generate unique pet data");
            PetData pet = PetData.getEmptyPetData().setName(generateRandomName() + id).setDateOfBirth(generateRandomPetDateOfBirth()).setPetType(petTypes.get(j));
            j++;
            OwnerSearchPage ownerSearchPage = homePage.openFindOwner();

            log.info("Start adding pet beginning from the first owner, when we run out of owners");
            if(petOwners == 0){
               petOwners = owners.size();
            }
            OwnerInfoPage ownerInfoPage = ownerSearchPage.searchOwner(owners.get(petOwners - 1).getLastName());

            log.info("Add pet to owner and to list of pets");
            ownerInfoPage.clickAddNewPetButton().addPet(pet);
            pets.add(pet);
            petOwners--;
        }

        log.info("Add owers and pets to the map");
        petOwnerAndPets.put(owners, pets);
        return petOwnerAndPets;
    }

    public PetOwnerData createNewPet(PetOwnerData owner, PetData pet) {

        log.info("Add pet to existing the owner");
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
        AddPetPage addPetPage = new AddPetPage(getDriver());
        addPetPage.addPet(pet);
        return owner;
    }

    public PetOwnerData createNewOwnerWithPet(PetOwnerData ownerData, PetData petData) {

        log.info("Create new owner and add new pet");
        PetOwnerData petOwner = createNewPetOwner(ownerData);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
        AddPetPage addPetPage = new AddPetPage(getDriver());
        addPetPage.addPet(petData);
        return petOwner;
    }


    public PetOwnerData createNewOwnerWithMultiplePets(PetOwnerData ownerData, List<PetData> pets) {

        log.info("Create new owner and add multiple new pet");
        PetOwnerData petOwner = createNewPetOwner(ownerData);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        AddPetPage addPetPage = new AddPetPage(getDriver());
        for (PetData pet : pets) {
            ownerInfoPage.clickAddNewPetButton();
            addPetPage.addPet(pet);
        }
        return petOwner;
    }

    public PetOwnerData addPetClinicVisitForNewOwner(String visitType) {

        log.info("Create new owner, add new pet new visit");
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();

        log.info("Generate data for new owner and create new owner");
        PetOwnerData owner =
                TestDataFactory.generateUniquePetOwnerData();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);

        log.info("Generate data for new pet and add new pet");
        PetData pet = PetData.getEmptyPetData().setName(generateRandomName()).setDateOfBirth("10.12.2024").setPetType(PetType.DOG);
        AddPetPage addPetPage = new AddPetPage(getDriver());
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
        addPetPage.addPet(pet);
        ownerInfoPage.clickVisitButton(1);
        AddVisitPage addVisitPage = new AddVisitPage(getDriver());

        log.info("Add new visit");
        addVisitPage.addVisit(visitType);
        return owner;
    }

    public PetOwnerData addPetClinicVisit(PetOwnerData owner, int petId, String visitType) {

        log.info("Add visit to existing pet");
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickVisitButton(petId);
        AddVisitPage addVisitPage = new AddVisitPage(getDriver());
        addVisitPage.addVisit(visitType);
        return owner;
    }


    public List<PetOwnerData> createMultiplePetOwner(int numberOfPetOwners) {

       log.info("Create multiple different pet owners");
        List<PetOwnerData> petOwners = new ArrayList<>();
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();

        while (numberOfPetOwners > 0) {
            PetOwnerData owner =
                    TestDataFactory.generateUniquePetOwnerData();
            OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
            AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
            addOwnerPage.createOwner(owner);
            numberOfPetOwners--;
            petOwners.add(owner);
        }
        return petOwners;
    }

    public OwnerInfoPage searchPetOwner(String lastName) {

        log.info("Search pet owner by last name");
        HomePage homePage = new HomePage(getDriver());
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        OwnerInfoPage ownerInfoPage = ownerSearchPage.searchOwner(lastName);
        return ownerInfoPage;
    }
}
