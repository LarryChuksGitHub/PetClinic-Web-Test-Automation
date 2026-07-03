package com.verimi.testcommon.flows;

import org.openqa.selenium.WebDriver;

import com.verimi.testcommon.pageobject.mobile.AiLandingScreen;
import com.verimi.testcommon.pageobject.mobile.DWelcomeScreen;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeutschlandAppFlow extends BasicFlow {


    public DeutschlandAppFlow(WebDriver driver) {
        super(driver);
    }


    public AiLandingScreen registerUserWithPhoneNumber(String email) {

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
                .addEmail(email)
                .navigateToOtpScreen()
                .enterOpt(email, TWO_FA_PIN)
                .navigateToTelePhoneScreen()
                .navigateToIdentityScreen(PHONE_NUMBER)
                .navigateToIdSelectionScreen()
                .selectIdCard()
                .assertEidScopeToBeDelivered()
                .navigateToEidPinScreen()
                .addEidCardPin(EID_PIN)
                .waitForEidData()
                .navigateToHomeScreen(TWO_FA_PIN)
                .assertHomeScreenContent();
        return new AiLandingScreen(getDriver());
    }

public AiLandingScreen registerUserWithoutPhoneNumber(String email) {

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
            .addEmail(email)
            .navigateToOtpScreen()
            .enterOpt(email, TWO_FA_PIN)
            .navigateToIdentityScreen()
            .navigateToIdSelectionScreen()
            .selectIdCard()
            .navigateToEidPinScreen()
            .addEidCardPin(EID_PIN)
            .waitForEidData()
            .navigateToHomeScreen(TWO_FA_PIN);
        return new AiLandingScreen(getDriver());
    }

}
