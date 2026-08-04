package com.tsys.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.tsys.testcommon.flows.DeutschlandAppFlow;
import com.tsys.testcommon.framework.drivers.DriverManager;
import com.tsys.testcommon.framework.utils.random.RandomUtilities;
import com.tsys.testcommon.pageobject.mobile.AdultChildSupportChatScreen;
import com.tsys.testcommon.pageobject.mobile.AiLandingScreen;
import com.tsys.testcommon.pageobject.mobile.AvatarScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@AllArgsConstructor
public class AdultChildSupportChatAvatarSteps {

    private static final String EMAIL = RandomUtilities.generateRandomEmail();

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Given("Die Nutzer ist registriert und eingeloggt")
    public void registerUser() {
        cleanAndSetUpTestData();
        DeutschlandAppFlow deutschlandAppFlow = new DeutschlandAppFlow(getDriver());
        deutschlandAppFlow.registerUserWithoutPhoneNumber(EMAIL);
    }

    @SneakyThrows
    @And("Der Nutzer befindet sich im Chat für Kindergeld ab 18")
    public void navigateToAdultChildChatScreen() {
        AiLandingScreen aiLandingScreen = new AiLandingScreen(getDriver());
        aiLandingScreen.selectChildSupportAndNavigateToAiIntroScreen()
                .assertScreenContent()
                .navigateToAdultChildSupportChatScreen();
    }


    @And("Wird nach Datenübernahme gefragt")
    public void chatAskIfUserDatenCanBeImported() {
        AdultChildSupportChatScreen adultChildSupportChatScreen = new AdultChildSupportChatScreen(getDriver());
        adultChildSupportChatScreen.importData();


    }


    @When("Der Nutzer die Datenübernahme bestätigt")
    public void userDontConfirmDataImport() {
        AdultChildSupportChatScreen adultChildSupportChatScreen = new AdultChildSupportChatScreen(getDriver());
        adultChildSupportChatScreen.confirmDataImport();
    }


    @And("Wird die Nutzerdatenübernahme erfolgreich durchgeführt")
    public void userDataImported() {
        AdultChildSupportChatScreen adultChildSupportChatScreen = new AdultChildSupportChatScreen(getDriver());
        adultChildSupportChatScreen.dataImported();
    }


    @When("Der Nutzer eine Frage {string} im Textfield eingibt")
    public void userAskForAdultChildSupportApplication(String text) {
        AdultChildSupportChatScreen adultChildSupportChatScreen = new AdultChildSupportChatScreen(getDriver());
        adultChildSupportChatScreen.askForAdultChildSupportApplication(text);
    }


    @Then("Die Antwort des KI-Bürgerservice wird erfolgreich angezeigt")
    public void correctAnswerShouldBeShown() {
        AdultChildSupportChatScreen adultChildSupportChatScreen = new AdultChildSupportChatScreen(getDriver());
        adultChildSupportChatScreen.waitForAnswer();
    }


    @And("Die Antwort enthält die Frage {string}")
    public void answerContainsEducationalInformationOfTheChild(String educationalInformation) {
        AdultChildSupportChatScreen adultChildSupportChatScreen = new AdultChildSupportChatScreen(getDriver());
        adultChildSupportChatScreen.waitForAnswerOptions();
    }

    @And("Kindergeld ab 18 Avatar is sichtbar")
    public void ensureK18AvatarIsShown() {
        AdultChildSupportChatScreen adultChildSupportChatScreen = new AdultChildSupportChatScreen(getDriver());
        adultChildSupportChatScreen.switchToAvatarScreen().assertAvatarScreenContent();
    }

    @And("Kindergeld ab 18 Mikrofone und Anhang sind sichtar")
    public void ensureK18MicAndAttachmentAreShown() {
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
