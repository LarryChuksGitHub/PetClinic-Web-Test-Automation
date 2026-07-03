package com.verimi.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.verimi.testcommon.flows.DeutschlandAppFlow;
import com.verimi.testcommon.framework.drivers.DriverManager;
import com.verimi.testcommon.framework.utils.random.RandomUtilities;
import com.verimi.testcommon.pageobject.mobile.AiLandingScreen;
import com.verimi.testcommon.pageobject.mobile.HousRegisterChatScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@AllArgsConstructor
public class HouseRegistrationChatSteps {

    private static final String EMAIL = RandomUtilities.generateRandomEmail();

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Given("Die Nutzer ist registriert und eingeloggt für eWA")
    public void registerUser() {
        cleanAndSetUpTestData();
        DeutschlandAppFlow deutschlandAppFlow = new DeutschlandAppFlow(getDriver());
        deutschlandAppFlow.registerUserWithoutPhoneNumber(EMAIL);
    }

    @SneakyThrows
    @And("Der Nutzer befindet sich im Chat für Wohnen und Umzug")
    public void navigateToAdultChildChatScreen() {
        AiLandingScreen aiLandingScreen = new AiLandingScreen(getDriver());
        aiLandingScreen.selectHouseRegistrationAndNavigateToAiIntroScreen()
                .navigateToHouseRegistrationChatScreen();
    }


    @And("Es wird nach Datenübernahme für eWA gefragt")
    public void chatAskIfUserDatenCanBeImported() {
        HousRegisterChatScreen housRegisterChatScreen = new HousRegisterChatScreen(getDriver());
        housRegisterChatScreen.importData();


    }


    @When("Der Nutzer die Datenübernahme für eWA bestätigt")
    public void userDontConfirmDataImport() {
        HousRegisterChatScreen housRegisterChatScreen = new HousRegisterChatScreen(getDriver());
        housRegisterChatScreen.confirmDataImport();
    }


    @And("Wird die Nutzerdatenübernahme für eWA erfolgreich durchgeführt")
    public void userDataIsImported() {
        HousRegisterChatScreen housRegisterChatScreen = new HousRegisterChatScreen(getDriver());
        housRegisterChatScreen.dataImported();
    }


    @And("eWA Chat Screen hat die richtige Inhalte")
    public void assertEWAChat() {
        HousRegisterChatScreen housRegisterChatScreen = new HousRegisterChatScreen(getDriver());
        housRegisterChatScreen.assertContent();
    }


    @When("Der Nutzer eine eWA Frage {string} im Textfield eingibt")
    public void userAskForHouseRegistrationApplication(String text) {
        HousRegisterChatScreen housRegisterChatScreen = new HousRegisterChatScreen(getDriver());
        housRegisterChatScreen.askForHouseRegistrationApplication(text);
    }


    @Then("Die Antwort enthält eWA Frage")
    public void answerContainsHouseRegistration() {
        HousRegisterChatScreen housRegisterChatScreen = new HousRegisterChatScreen(getDriver());
        housRegisterChatScreen.waitForAnswerOptions();
    }

    @SneakyThrows
    private void cleanAndSetUpTestData() {
        log.info("Clean up TestData");
    }

}
