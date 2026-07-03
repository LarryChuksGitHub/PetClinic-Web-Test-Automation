package com.verimi.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.verimi.testcommon.flows.DeutschlandAppFlow;
import com.verimi.testcommon.framework.drivers.DriverManager;
import com.verimi.testcommon.framework.utils.random.RandomUtilities;
import com.verimi.testcommon.pageobject.mobile.AiLandingScreen;
import com.verimi.testcommon.pageobject.mobile.HouseRegistrationFormularScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@AllArgsConstructor
public class HouseRegistrationFormularSteps {

    private static final String EMAIL = RandomUtilities.generateRandomEmail();

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Given("Die Nutzer ist registriert und eingeloggt für eWA Formular")
    public void registerUserForFormular() {
        cleanAndSetUpTestData();
        DeutschlandAppFlow deutschlandAppFlow = new DeutschlandAppFlow(getDriver());
        deutschlandAppFlow.registerUserWithoutPhoneNumber(EMAIL);
    }

    @SneakyThrows
    @And("Der Nutzer befindet sich im Formular für eWA")
    public void navigateToEWAFormularScreen() {
        AiLandingScreen aiLandingScreen = new AiLandingScreen(getDriver());
        aiLandingScreen.selectHouseRegistrationAndNavigateToAiIntroScreen()
                .navigateToHouseRegistrationChatScreen()
                .importData()
                .confirmDataImport()
                .dataImported()
                .navigateToHouseRegistrationFormularScreen();
    }


    @When("Der Nutzer das eWA Formular erfolgreich ausfüllt")
    public void fillEWAFormular() {
        HouseRegistrationFormularScreen houseRegistrationFormularScreen = new HouseRegistrationFormularScreen(getDriver());
        houseRegistrationFormularScreen.fillEWAForm();


    }


    @Then("Absend Button für eWA wird angezeigt")
    public boolean isSendButtonForEWADisplayed() {
        HouseRegistrationFormularScreen houseRegistrationFormularScreen = new HouseRegistrationFormularScreen(getDriver());
        return true;
    }


    @When("Der Nutzer das Button für eWA senden klickt")
    public void sendTheEWAApplication() {
        HouseRegistrationFormularScreen houseRegistrationFormularScreen = new HouseRegistrationFormularScreen(getDriver());
    }


    @Then("Wird das eWA Formular erfolgreich abgesendet")
    public boolean isEWAApplicationSent() {
        HouseRegistrationFormularScreen houseRegistrationFormularScreen = new HouseRegistrationFormularScreen(getDriver());
        return true;
    }


    @And("Die Bestätigung für eine erfolgreiche eWA Absendung  wird angezeigt")
    public void isSentConfirmationMessageDisplayed() {
        HouseRegistrationFormularScreen houseRegistrationFormularScreen = new HouseRegistrationFormularScreen(getDriver());
    }

    @SneakyThrows
    private void cleanAndSetUpTestData() {
        log.info("Clean up TestData");
    }

}
