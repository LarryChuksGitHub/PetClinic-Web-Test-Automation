package com.verimi.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.verimi.testcommon.flows.DeutschlandAppFlow;
import com.verimi.testcommon.framework.drivers.DriverManager;
import com.verimi.testcommon.framework.utils.random.RandomUtilities;
import com.verimi.testcommon.pageobject.mobile.AiLandingScreen;
import com.verimi.testcommon.pageobject.mobile.AvatarScreen;
import com.verimi.testcommon.pageobject.mobile.HouseRegisterChatScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@AllArgsConstructor
public class HouseRegistrationChatAvatarSteps {

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
        HouseRegisterChatScreen houseRegisterChatScreen = new HouseRegisterChatScreen(getDriver());
        houseRegisterChatScreen.importData();


    }


    @When("Der Nutzer die Datenübernahme für eWA bestätigt")
    public void userDontConfirmDataImport() {
        HouseRegisterChatScreen houseRegisterChatScreen = new HouseRegisterChatScreen(getDriver());
        houseRegisterChatScreen.confirmDataImport();
    }


    @And("Wird die Nutzerdatenübernahme für eWA erfolgreich durchgeführt")
    public void userDataIsImported() {
        HouseRegisterChatScreen houseRegisterChatScreen = new HouseRegisterChatScreen(getDriver());
        houseRegisterChatScreen.dataImported();
    }


    @And("eWA Chat Screen hat die richtige Inhalte")
    public void assertEWAChat() {
        HouseRegisterChatScreen houseRegisterChatScreen = new HouseRegisterChatScreen(getDriver());
        houseRegisterChatScreen.assertContent();
    }


    @When("Der Nutzer eine eWA Frage {string} im Textfield eingibt")
    public void userAskForHouseRegistrationApplication(String text) {
        HouseRegisterChatScreen houseRegisterChatScreen = new HouseRegisterChatScreen(getDriver());
        houseRegisterChatScreen.askForHouseRegistrationApplication(text);
    }


    @Then("Die Antwort enthält eWA Frage")
    public void answerContainsHouseRegistration() {
        HouseRegisterChatScreen houseRegisterChatScreen = new HouseRegisterChatScreen(getDriver());
        houseRegisterChatScreen.waitForAnswerOptions();
    }

    @And("eWA Avatar is sichtbar")
    public void ensureEwaAvatarIsShown() {
        HouseRegisterChatScreen houseRegisterChatScreen = new HouseRegisterChatScreen(getDriver());
        houseRegisterChatScreen.switchToAvatarScreen().assertAvatarScreenContent();
    }

    @And("eWA Mikrofone und Anhang sind sichtbar")
    public void ensureEwaMicAndAttachmentAreShown() {
        AvatarScreen avatarScreen = new AvatarScreen(getDriver());
        avatarScreen
                .checkMic()
                .navigateToAttachmentScreen()
                .assertAttachmentScreenContent()
                .navigateBackToAvatar();
    }

    @SneakyThrows
    private void cleanAndSetUpTestData() {
        log.info("Clean up TestData");
    }

}
