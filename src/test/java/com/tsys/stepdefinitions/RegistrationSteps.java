package com.tsys.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.tsys.testcommon.framework.drivers.DriverManager;
import com.tsys.testcommon.framework.utils.random.RandomUtilities;
import com.tsys.testcommon.pageobject.mobile.DWelcomeScreen;
import com.tsys.testcommon.pageobject.mobile.DataProtectionTermsAndConditionScreen;
import com.tsys.testcommon.pageobject.mobile.EidScreen;
import com.tsys.testcommon.pageobject.mobile.EmailScreen;
import com.tsys.testcommon.pageobject.mobile.OtpScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class RegistrationSteps {

    public static String TWO_FA_PIN = "123455";
    public static String EID_PIN = "123456";
    private static final String EMAIL = RandomUtilities.generateRandomEmail();
    public static String PHONE_NUMBER = "17681066891";
    public static String POSTCODE = "12279";


    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Given("Die Deutschland App ist gestartet")
    public void launchApplication() {
        cleanAndSetUpTestData(EMAIL);

    }

    @SneakyThrows
    @And("Der Nutzer befindet sich im Onboarding und die Mock eiD aktiviert")
    public void startOnboarding() {
        DWelcomeScreen dWelcomeScreen = new DWelcomeScreen(getDriver());
        dWelcomeScreen
                .navigateToAdultChildSupportOnboardingScreen()
                .activateMockEid()
                .navigateToHouseRegistrationOnboardingScreen()
                .navigateToDataProtectionOnboardingScreen()
                .navigateToTermsAndCondition();
    }

    @And("Der Nutzer die Registrierung startet")
    public void startRegistration() {
        DataProtectionTermsAndConditionScreen dataProtectionTermsAndConditionScreen = new DataProtectionTermsAndConditionScreen(getDriver());
        dataProtectionTermsAndConditionScreen.acceptTermsAndCondition()
                .navigateToPostcodeScreen()
                .addPostcode(POSTCODE)
                .navigateToRegistrationScreen()
                .starRegistration()
                .navigateTo2FaSetUpScreen()
                .setUp2FaPin(TWO_FA_PIN + TWO_FA_PIN)
                .navigateToEmailScreen();
    }

    @And("Eine gültige E-Mail-Adresse eingibt")
    public void enterEmail() {
        EmailScreen emailScreen = new EmailScreen(getDriver());
        emailScreen.addEmail(EMAIL)
                .navigateToOtpScreen();
    }

    @And("Das OTP erfolgreich bestätigt")
    public void confirmOtp() {
        OtpScreen otpScreen = new OtpScreen(getDriver());
        otpScreen.enterOpt(EMAIL, TWO_FA_PIN)
                .navigateToIdentityScreen()
                .navigateToIdSelectionScreen()
                .selectIdCard()
                .navigateToEidPinScreen()
                .addEidCardPin(EID_PIN);

    }

    @Then("Wird die Registrierung erfolgreich abgeschlossen und ID Daten werden unter Nachweise angezeigt")
    public void verifyRegistrationSuccess() {
        EidScreen eidScreen = new EidScreen(getDriver());
        eidScreen.waitForEidData()
                .navigateToHomeScreen(TWO_FA_PIN);
    }

    @SneakyThrows
    private void cleanAndSetUpTestData(String email) {

        log.info("Clean up existing user");

    }

}
