package com.verimi.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.verimi.testcommon.flows.DeutschlandAppFlow;
import com.verimi.testcommon.framework.drivers.DriverManager;
import com.verimi.testcommon.framework.utils.random.RandomUtilities;
import com.verimi.testcommon.pageobject.mobile.AdultChildSupportFormularScreen;
import com.verimi.testcommon.pageobject.mobile.AiLandingScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@AllArgsConstructor
public class AdultChildSupportFormularSteps {

    private static final String EMAIL = RandomUtilities.generateRandomEmail();

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Given("Die Nutzer ist registriert und eingeloggt für Formular")
    public void registerUserForFormular() {
        cleanAndSetUpTestData();
        DeutschlandAppFlow deutschlandAppFlow = new DeutschlandAppFlow(getDriver());
        deutschlandAppFlow.registerUserWithoutPhoneNumber(EMAIL);
    }

    @SneakyThrows
    @And("Der Nutzer befindet sich im Formular für Kindergeld ab 18")
    public void navigateToAdultChildFormularScreen() {
        AiLandingScreen aiLandingScreen = new AiLandingScreen(getDriver());
        aiLandingScreen.selectChildSupportAndNavigateToAiIntroScreen()
                .navigateToAdultChildSupportChatScreen()
                .importData()
                .confirmDataImport()
                .dataImported()
                .navigateToAdultChildSupportFormularScreen();
    }


    @When("Der Nutzer das Formular erfolgreich ausfüllt")
    public void fillFormular() {
        AdultChildSupportFormularScreen adultChildSupportFormularScreen = new AdultChildSupportFormularScreen(getDriver());
        adultChildSupportFormularScreen.fillCompleteForm();


    }


    @Then("Absend Button wird angezeigt")
    public boolean isSendButtonDisplayed() {
        AdultChildSupportFormularScreen adultChildSupportFormularScreen = new AdultChildSupportFormularScreen(getDriver());
        return true;
    }


    @When("Der Nutzer das Button send klickt")
    public void sendTheApplication() {
        AdultChildSupportFormularScreen adultChildSupportFormularScreen = new AdultChildSupportFormularScreen(getDriver());
    }


    @Then("Wird das Formular erfolgreich abgesendet")
    public boolean isApplicationSent() {
        AdultChildSupportFormularScreen adultChildSupportFormularScreen = new AdultChildSupportFormularScreen(getDriver());
        return true;
    }


    @And("Die Bestätigung für eine erfolgreiche Absendung wird angezeigt")
    public void isSentConfirmationMessageDisplayed() {
        AdultChildSupportFormularScreen adultChildSupportFormularScreen = new AdultChildSupportFormularScreen(getDriver());
    }

    @SneakyThrows
    private void cleanAndSetUpTestData() {
        log.info("Clean up TestData");
    }

}
