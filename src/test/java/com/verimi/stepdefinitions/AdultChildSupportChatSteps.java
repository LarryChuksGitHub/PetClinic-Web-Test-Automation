package com.verimi.stepdefinitions;

import static com.verimi.stepdefinitions.RegistrationSteps.EID_PIN;
import static com.verimi.stepdefinitions.RegistrationSteps.PHONE_NUMBER;
import static com.verimi.stepdefinitions.RegistrationSteps.POSTCODE;
import static com.verimi.stepdefinitions.RegistrationSteps.TWO_FA_PIN;

import org.openqa.selenium.WebDriver;

import com.verimi.testcommon.framework.drivers.DriverManager;
import com.verimi.testcommon.framework.utils.random.RandomUtilities;
import com.verimi.testcommon.pageobject.mobile.AdultChildSupportChatScreen;
import com.verimi.testcommon.pageobject.mobile.AiLandingScreen;
import com.verimi.testcommon.pageobject.mobile.DWelcomeScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@AllArgsConstructor
public class AdultChildSupportChatSteps {

    private static final String EMAIL = RandomUtilities.generateRandomEmail();

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Given("Die Nutzer ist registriert und eingeloggt")
    public void registerUser() {
        cleanAndSetUpTestData();

        DWelcomeScreen dWelcomeScreen = new DWelcomeScreen(getDriver());
        dWelcomeScreen
                .navigateToAdultChildSupportOnboardingScreen()
                .activateMockEid()
                .navigateToHouseRegistrationOnboardingScreen()
                .navigateToDataProtectionOnboardingScreen()
                .navigateToTermsAndCondition()
                .acceptTermsAndCondition()
                .navigateToPostcodeScreen()
                .addPostcode(POSTCODE)
                .navigateToRegistrationScreen()
                .starRegistration()
                .navigateTo2FaSetUpScreen()
                .setUp2FaPin(TWO_FA_PIN + TWO_FA_PIN)
                .navigateToEmailScreen()
                .addEmail(EMAIL)
                .navigateToOtpScreen()
                .enterOpt(EMAIL, TWO_FA_PIN)
                .navigateToTelePhoneScreen()
                .navigateToIdentityScreen(PHONE_NUMBER)
                .navigateToIdSelectionScreen()
                .selectIdCard()
                .navigateToEidPinScreen()
                .addEidCardPin(EID_PIN)
                .waitForEidData()
                .navigateToHomeScreen(TWO_FA_PIN);
    }

    @SneakyThrows
    @And("Der Nutzer befindet sich im Chat für Kindergeld ab 18")
    public void navigateToAdultChildChatScreen() {
        AiLandingScreen aiLandingScreen = new AiLandingScreen(getDriver());
        aiLandingScreen.navigateToAiIntroScreen()
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
    public void userDataIsNotImported() {
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

    @SneakyThrows
    private void cleanAndSetUpTestData() {
        log.info("Clean up TestData");
    }

}
