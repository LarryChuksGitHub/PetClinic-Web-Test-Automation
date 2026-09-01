package com.petclinic.frontendtest.web;


import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_REGRESSION_PETCLINIC;
import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_SMOKE_PETCLINIC;
import static com.petclinic.testcommon.framework.utils.random.RandomUtilities.generateRandomName;
import static com.petclinic.testcommon.framework.utils.random.RandomUtilities.generateRandomStringDigit;
import static com.petclinic.testcommon.framework.utils.random.RandomUtilities.generateRandomUID;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import com.petclinic.testcommon.config.hooks.Hooks;
import com.petclinic.testcommon.flows.PetClinicFlow;
import com.petclinic.testcommon.framework.asserts.DippSoftAssertions;
import com.petclinic.testcommon.framework.utils.constant.NumericConstants;
import com.petclinic.testcommon.framework.utils.retry.RetryUtils;
import com.petclinic.testcommon.model.petclinic.PetOwnerData;
import com.petclinic.testcommon.pageobject.web.petclinic.AddOwnerPage;
import com.petclinic.testcommon.pageobject.web.petclinic.HomePage;
import com.petclinic.testcommon.pageobject.web.petclinic.OwnerInfoPage;
import com.petclinic.testcommon.pageobject.web.petclinic.OwnerSearchPage;
import com.petclinic.testcommon.testdata.petclinic.TestDataFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PetOwnerTests extends Hooks {

    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    private void setUp() {
        homePage = new HomePage(getDriver());
        homePage.openHomePage();
        softAssertion = new DippSoftAssertions();
    }

    @Tag(name = "major")
    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @DisplayName("TC-OWN-003: Nicht existierender Owner liefert kein Ergebnis")
    void shouldNotFindUnknownOwner() {
        String searchText = "OwnerDoesNotExist";
        OwnerInfoPage OwnerInfoPage = searchPetOwner(searchText);

        log.info("Very no owner was found ");
        assertFalse(OwnerInfoPage.isOwnerDisplayed(searchText),
                "PetData owner that does not exist should not be found."
        );
    }

    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-OWN-006: Neuer Owner angelegen und wieder finden")
    void shouldCreateAndFindOwner() {
        setUp();
        PetOwnerData owner =
                TestDataFactory.uniqueOwner();
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        OwnerInfoPage ownerInfoPage = addOwnerPage.createOwner(owner);

        log.info("Very no owner found ");
        assertTrue(ownerInfoPage.isPetOwnerCreated(owner),
                "Newly created owner " + owner.getLastName() + " should be created."
        );

        ownerInfoPage = homePage.openFindOwner().searchOwner(owner.getLastName());
        assertTrue(ownerInfoPage.isPetOwnerNameDisplayed(owner.getLastName()),
                "Existing Owner " + owner.getLastName() + " should be displayed.");
    }


    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name = "major")
    @DisplayName("TC-OWN-012: Mehrere Owner mit demselben Namen")
    void shouldCreateOwnersWithSameName() {
        setUp();
        PetOwnerData owner =
                TestDataFactory.uniqueOwner();
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        int numberOfOwner = 3;
        List<PetOwnerData> owners = petClinicFlow.createNewPetOwner(owner, numberOfOwner);

        new OwnerInfoPage(getDriver());
        OwnerInfoPage ownerInfoPage;
        ownerInfoPage = homePage.openFindOwner().searchOwner(owner.getLastName());

        log.info("Very with same data are found");
        assertEquals(owners.size(), numberOfOwner, "Owners with same lastnames " + owner.getLastName() + " should be created");

        assertTrue(ownerInfoPage.arePetOwnerNamesDisplayed(numberOfOwner, owner.getFirstName() + " "+owner.getLastName()),
                "Owners with same lastnames " + owner.getLastName() + " should be created");
    }


    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-OWN-008: Neuer Owner angelegen ohne Nachname nicht möglich")
    void shouldNotCreateOwnerWithoutLastName() {
        setUp();
        PetOwnerData owner =
                TestDataFactory.uniqueOwner().setLastName("");
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();
        addOwnerPage.createOwner(owner);

        log.info("Very no owner without lastname was created ");
        assertTrue(RetryUtils.isConditionFulfilledWithWait(() -> Objects.requireNonNull(getDriver().getPageSource()).contains("must not be blank"), NumericConstants.NUMERIC_5),
                "It should not be possible to create owner without last name"
        );
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-OWN-009: PetData Owner Felder soll validiert werden")
    void shouldHandleOwnerFieldBoundary() {
        setUp();
        PetOwnerData owner =
                TestDataFactory.uniqueOwner().setLastName("");
        OwnerSearchPage ownerSearchPage = homePage.openFindOwner();
        AddOwnerPage addOwnerPage = ownerSearchPage.clickAddOwner();

        // All fields are empty
        owner.setFirstName("").setLastName("").setAddress("").setCity("").setTelephone("");
        addOwnerPage.createOwner(owner);

        log.info("Very the number of error messages in the page ");
        assertTrue(addOwnerPage.getNumberOfErrors() == 5,
                "Error messages should be 5"
        );

        // Fields exceeds max length
        owner
                .setFirstName(generateRandomUID().substring(0, 31))
                .setLastName(generateRandomUID().substring(0, 31))
                .setAddress(generateRandomUID().substring(0, 31))
                .setCity(generateRandomUID().substring(0, 31))
                .setTelephone(generateRandomStringDigit(11));

        addOwnerPage.createOwner(owner);
        assertTrue(addOwnerPage.getNumberOfErrors() == 3,
                "Error messages should be 3"
        );

        // Telephone field not digital
        owner.setFirstName("a")
                .setLastName("b")
                .setAddress("b")
                .setCity("c")
                .setTelephone(generateRandomUID().substring(0, 11));

        addOwnerPage.createOwner(owner);
        assertTrue(RetryUtils.isConditionFulfilledWithWait(() -> Objects.requireNonNull(getDriver().getPageSource()).contains("Telephone must be a 10-digit number"), NumericConstants.NUMERIC_5),
                "Telephone digit error messages should be displayed"
        );

        // Accept min input in all fields
        owner
                .setTelephone(generateRandomStringDigit(10));

        OwnerInfoPage ownerInfoPage = addOwnerPage.createOwner(owner);
        assertTrue(ownerInfoPage.isPetOwnerCreated(owner),
                "Newly created owner " + owner.getLastName() + " should be created."
        );

    }


    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-OWN-002: Owner kann mit Teil des Nachnamens gesucht werden")
    void shouldFindOwnerUsingPartialLastName() {
        setUp();
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        PetOwnerData petOwner = TestDataFactory.uniqueOwner();
        petOwner.setLastName(generateRandomName() + generateRandomUID().substring(0, 4));
        petOwner = petClinicFlow.createNewPetOwner(petOwner);
        OwnerInfoPage ownerInfoPage = homePage.openFindOwner().searchOwner(petOwner.getLastName().substring(0, 4));

        log.info("Very owner was found ");
        assertTrue(ownerInfoPage.isPetOwnerNameDisplayed(petOwner.getLastName()), "Should Find Owner via partial last name");
    }


    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-OWN-004: Leere Owner-Suche wird kontrolliert verarbeitet")
    void shouldHandleEmptyOwnerSearch() {
        String searchText = "";
        searchPetOwner(searchText);

        log.info("Very list of owners were found");
        assertTrue(
                getDriver().getCurrentUrl()
                        .contains("/owners"),
                "Die Anwendung sollte die Anfrage kontrolliert verarbeiten."
        );
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-OWN-010: Owner-Suche verarbeitet Sonderzeichen")
    void shouldHandleSpecialCharacters() {
        String searchText = "MÜ&%$§";
        searchPetOwner(searchText);

        log.info("Very no owner was found and app does not crash");
        assertTrue(RetryUtils.isConditionFulfilledWithWait(() -> Objects.requireNonNull(getDriver().getPageSource()).contains("has not been found"), NumericConstants.NUMERIC_5),
                "Special character should not crash the app."
        );
    }

    private OwnerInfoPage searchPetOwner(String searchText) {
        setUp();
        return homePage.openFindOwner().searchOwner(searchText);
    }


    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-OWN-011: Owner erfolgreich bearbeiten")
    void shouldEditPetOwner() {
        PetOwnerData petOwner = TestDataFactory.uniqueOwner();
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.createNewPetOwner(petOwner);
        OwnerInfoPage ownerInfoPage = searchPetOwner(petOwner.getLastName());
        PetOwnerData petOwner1 = TestDataFactory.uniqueOwner();
        ownerInfoPage.editOwner(petOwner1);

        log.info("Verify edited ower is visible");
        assertTrue(ownerInfoPage.isPetOwnerCreated(petOwner1),
                "Updated owner " + petOwner1.getLastName() + " should be visible."
        );
    }

}
