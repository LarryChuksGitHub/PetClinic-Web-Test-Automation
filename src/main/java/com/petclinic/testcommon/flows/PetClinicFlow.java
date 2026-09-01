package com.petclinic.testcommon.flows;

import static com.petclinic.testcommon.framework.utils.random.RandomUtilities.generateRandomName;
import static com.petclinic.testcommon.pageobject.web.petclinic.HomePage.BASE_URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        PetOwnerData owner =
                TestDataFactory.uniqueOwner();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);
        return owner;
    }

    public PetOwnerData createNewPetOwner(PetOwnerData owner) {
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);
        return owner;
    }

    public List<PetOwnerData> createNewPetOwner(PetOwnerData owner, int numberOfOwner) {
        HomePage homePage = new HomePage(getDriver());
        List<PetOwnerData> owners = new ArrayList<>();
        while (numberOfOwner > 0) {
            OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
            AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
            addOwnerPage.createOwner(owner);
            owners.add(owner);
            numberOfOwner--;
        }
        return owners;
    }


    public PetOwnerData editPetOwner(PetOwnerData petOwner) {
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();
        PetOwnerData owner =
                TestDataFactory.uniqueOwner();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.editOwner(petOwner);
        return petOwner;
    }

    public PetOwnerData createNewPet() {
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();
        PetOwnerData owner =
                TestDataFactory.uniqueOwner();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);
        String id = RandomUtilities.generateRandomUID();
        PetData pet = PetData.getEmptyPetData().setName("Bello" + id).setDateOfBirth("2020-05-10").setPetType(PetType.DOG);
        AddPetPage addPetPage = new AddPetPage(getDriver());
        addPetPage.addPet(pet);
        return owner;
    }

    public OwnerInfoPage addDifferentPets(int numberOfPets) {
        HomePage homePage = new HomePage(getDriver());
        PetOwnerData owner =
                TestDataFactory.uniqueOwner();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);
        List<PetType> pets = Arrays.stream(PetType.values()).toList();
        int j = 0;
        OwnerInfoPage ownerInfoPage = null;
        for (int i = 0; i < numberOfPets; i++) {
            String id = RandomUtilities.generateRandomUID().substring(0, 10);
            if (i > pets.size() - 1 && (i == j || i % pets.size() == 0)) {
                pets = Arrays.stream(PetType.values()).toList();
                j = 0;
            }
            PetData pet = PetData.getEmptyPetData().setName(generateRandomName() + id).setDateOfBirth("10.11.2020").setPetType(pets.get(j));
            j++;
            ownerInfoPage = new OwnerInfoPage(getDriver());
            ownerInfoPage.clickAddNewPetButton().addPet(pet);
        }
        return ownerInfoPage;
    }

    public PetOwnerData createNewPet(PetOwnerData owner, PetData pet) {
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
        AddPetPage addPetPage = new AddPetPage(getDriver());
        addPetPage.addPet(pet);
        return owner;
    }

    public PetOwnerData createNewOwnerWithPet(PetOwnerData ownerData, PetData petData) {
        PetOwnerData petOwner = createNewPetOwner(ownerData);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
        AddPetPage addPetPage = new AddPetPage(getDriver());
        addPetPage.addPet(petData);
        return petOwner;
    }

    public PetOwnerData addPetClinicVisitForNewOwner(String visitType) {
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();
        PetOwnerData owner =
                TestDataFactory.uniqueOwner();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);


        PetData pet = PetData.getEmptyPetData().setName(generateRandomName()).setDateOfBirth("10.12.2024").setPetType(PetType.DOG);
        AddPetPage addPetPage = new AddPetPage(getDriver());
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickAddNewPetButton();
        addPetPage.addPet(pet);
        ownerInfoPage.clickVisitButton(1);
        AddVisitPage addVisitPage = new AddVisitPage(getDriver());
        addVisitPage.addVisit(visitType);
        return owner;
    }

    public PetOwnerData addPetClinicVisit(PetOwnerData owner, int petId, String visitType) {
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickVisitButton(petId);
        AddVisitPage addVisitPage = new AddVisitPage(getDriver());
        addVisitPage.addVisit(visitType);
        return owner;
    }


    public List<PetOwnerData> createMultiplePetOwner(int numberOfPetOwners) {
        List<PetOwnerData> petOwners = new ArrayList<>();
        HomePage homePage = new HomePage(getDriver());
        homePage.openHomePage();

        while (numberOfPetOwners > 0) {
            PetOwnerData owner =
                    TestDataFactory.uniqueOwner();
            OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
            AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
            addOwnerPage.createOwner(owner);
            numberOfPetOwners--;
            petOwners.add(owner);
        }
        return petOwners;
    }

    public OwnerInfoPage searchPetOwner(String lastName) {
        HomePage homePage = new HomePage(getDriver());
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        OwnerInfoPage ownerInfoPage = ownerSearchPage.searchOwner(lastName);
        return ownerInfoPage;
    }
}
