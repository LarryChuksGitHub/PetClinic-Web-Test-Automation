package com.petclinic.frontendtest.web;


import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_REGRESSION_PETCLINIC;
import static com.petclinic.testcommon.framework.testgroup.TestGroup.FRONTEND_SMOKE_PETCLINIC;
import static org.testng.Assert.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import com.petclinic.testcommon.config.hooks.Hooks;
import com.petclinic.testcommon.flows.PetClinicFlow;
import com.petclinic.testcommon.framework.utils.constant.NumericConstants;
import com.petclinic.testcommon.framework.utils.retry.RetryUtils;
import com.petclinic.testcommon.model.petclinic.PetData;
import com.petclinic.testcommon.model.petclinic.PetOwnerData;
import com.petclinic.testcommon.pageobject.web.petclinic.AddVisitPage;
import com.petclinic.testcommon.pageobject.web.petclinic.OwnerInfoPage;
import com.petclinic.testcommon.testdata.petclinic.TestDataFactory;


public class PetClinicVisitTests extends Hooks {

    @Test(groups = {FRONTEND_SMOKE_PETCLINIC})
    @Tag(name  ="major")
    @DisplayName("TC-VIS-001: Neuer Tierarztbesuch kann angelegt werden")
    void shouldCreateVisit() {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        String visitType = "Jährliche Untersuchung";
        petClinicFlow.addPetClinicVisitForNewOwner(visitType);
        assertTrue(RetryUtils.isConditionFulfilledWithWait(()-> Objects.requireNonNull(getDriver().getPageSource()).contains(visitType), NumericConstants.NUMERIC_4),
                "Der VisitData sollte angezeigt werden.");
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name  ="medium")
    @DisplayName("TC-VIS-001: Mehrere Tierarztbesuche kann angelegt werden")
    void shouldCreateMultipleVisitsForPet() {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        String visitType1 = "Jährliche Untersuchung";

        PetOwnerData petOwner = petClinicFlow.addPetClinicVisitForNewOwner(visitType1);
        assertTrue(RetryUtils.isConditionFulfilledWithWait(()-> Objects.requireNonNull(getDriver().getPageSource()).contains(visitType1), NumericConstants.NUMERIC_4),
                "VisitData "+ visitType1 +" should be shown.");

        String visitType2 = "Beine Check up";
        petClinicFlow.addPetClinicVisitForNewOwner(visitType2);
        petClinicFlow.addPetClinicVisit(petOwner, 1, visitType2);
        assertTrue(RetryUtils.isConditionFulfilledWithWait(()-> Objects.requireNonNull(getDriver().getPageSource()).contains(visitType2), NumericConstants.NUMERIC_4),
                "VisitData "+ visitType2 +" should be shown.");

        String visitType3 = "Zähne OP";
        petClinicFlow.addPetClinicVisit(petOwner, 1, visitType3);
        assertTrue(RetryUtils.isConditionFulfilledWithWait(()-> Objects.requireNonNull(getDriver().getPageSource()).contains(visitType3), NumericConstants.NUMERIC_4),
                "VisitData "+ visitType3 +" should be shown.");
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName("TC-VIS-003: VisitData ohne Beschreibung"
    )
    void shouldValidateEmptyVisitDescription() {
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.addPetClinicVisitForNewOwner("");

        assertTrue(
                getDriver().getCurrentUrl()
                        .contains("/visits/new")
        );
    }

    @Test(groups = {FRONTEND_REGRESSION_PETCLINIC})
    @Tag(name = "medium")
    @DisplayName(
            "TC-VIS-004: VisitData mit langer Beschreibung"
    )
    void shouldHandleLongVisitDescription() {
        PetOwnerData petOwnerData = TestDataFactory.uniqueOwner();
        PetData petData = TestDataFactory.uniquePet();
        PetClinicFlow petClinicFlow = new PetClinicFlow(getDriver());
        petClinicFlow.createNewOwnerWithPet(petOwnerData, petData);
        OwnerInfoPage ownerInfoPage = new OwnerInfoPage(getDriver());
        ownerInfoPage.clickVisitButton(1);
        AddVisitPage addVisitPage = new AddVisitPage(getDriver());

        String longDescription =
                "A".repeat(500);

        addVisitPage.addVisit(
                longDescription);

        assertTrue(
                RetryUtils.isConditionFulfilledWithWait(()-> Objects.requireNonNull(getDriver().getPageSource()).contains("An internal server error occurred"), NumericConstants.NUMERIC_8),
                "Long description should not be allowed ."
        );
    }

}
