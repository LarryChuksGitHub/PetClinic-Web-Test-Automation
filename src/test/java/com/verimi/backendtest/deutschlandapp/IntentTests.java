package com.verimi.backendtest.deutschlandapp;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.verimi.testcommon.config.hooks.Hooks;
import com.verimi.testcommon.endpointactions.api.AiResponse;
import com.verimi.testcommon.endpointactions.api.IntentType;
import com.verimi.testcommon.model.common.dataprovider.GoldenDatasetEntry;
import com.verimi.testcommon.model.common.dataprovider.GoldenDatasetGenerator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class IntentTests extends Hooks {

    /**
     * Scenario: AI Intent
     * Given: Intent API is active and running
     * When: User give child support from birth Intent as input to Intent API
     * Then: API should return child support for underage intent with confidence score and other details
     */
    @Test
    public void shouldRecognizeChildSupportFromBirthIntent() {
        String input = "Ich habe ein Kind bekommen, was muss ich tun?";

        AiResponse response = AiResponse.getIntent(input);
        printIntentOptions(response.getIntent());

        assertThat(response.getIntent()).isEqualTo(IntentType.CHILD_SUPPORT_FROM_BIRTH);
        assertThat(response.getConfidence()).isGreaterThan(0.85);
    }

    /**
     * Scenario: AI Intent
     * Given: Intent API is active and running
     * When: User give child support For adult Intent as input to Intent API
     * Then: API should return child support for adult intent with confidence score and other details
     */
    @Test
    public void shouldRecognizeChildSupportForAdultIntent() {
        String input = "Ich habe ein volljähriges Kind, was muss ich tun?";

        AiResponse response = AiResponse.getIntent(input);
        printIntentOptions(response.getIntent());

        assertThat(response.getIntent()).isEqualTo(IntentType.CHILD_SUPPORT_FOR_ADULT);
        assertThat(response.getConfidence()).isGreaterThan(0.85);
    }

    /**
     * Scenario: AI Intent
     * Given: Intent API is active and running
     * When: User give child support Intent as input to Intent API
     * Then: API should return list of child support intent with confidence score and other details
     */
    @Test
    public void shouldListChildSupportIntentOptions() {
        String input = "Ich habe ein Kind, was muss ich tun?";

        AiResponse response = AiResponse.getIntent(input);
        printIntentOptions(response.getIntent());

        assertThat(response.getIntent()).isEqualTo(IntentType.CHILD_SUPPORT_FROM_BIRTH_OR_AS_ADULT);
        assertThat(response.getConfidence()).isLessThan(0.6);
    }


    /**
     * Scenario: AI Intent
     * Given: Intent API is active and running
     * When: User give house registration Intent as input to Intent API
     * Then: API should return house registration intent with confidence score and other details
     */
    @Test
    public void shouldRecognizeHouseRegistrationIntent() {
        String input = "Ich bin umgezogen, was muss ich tun?";

        AiResponse response = AiResponse.getIntent(input);
        printIntentOptions(response.getIntent());

        assertThat(response.getIntent()).isEqualTo(IntentType.HOUSE_REGISTRATION);
        assertThat(response.getConfidence()).isGreaterThan(0.85);
    }

    /**
     * Scenario: AI Intent
     * Given: Intent API is active and running
     * When: User give house registration Intent as input to Intent API
     * Then: API should return house registration intent with confidence score and other details
     */
    @Test
    public void shouldReturnListOfOptions() {
        String input = "Ich brauche ein rezept für pizza salami, was muss ich tun?";

        AiResponse response = AiResponse.getIntent(input);
        printIntentOptions(response.getIntent());

        assertThat(response.getIntent()).isEqualTo(IntentType.LIST_ALL_INTENT_OPTIONS);
        assertThat(response.getConfidence()).isEqualTo(0.0);
    }


    /**
     * Scenario: AI Intent
     * Given: Intent API is active and running
     * When: User give input to Intent API
     * Then: API should return correct intent with confidence score and other details
     */
    @SneakyThrows
    @Test(groups = {})
    public void shouldDetectIntentOrListOptions() {
        log.info("Test Setup");

        List<GoldenDatasetEntry> dataset =
                GoldenDatasetGenerator.generateDataset();

        for (GoldenDatasetEntry entry : dataset) {

            log.info("-------------------------------------------------");

            log.info("User input: {}", entry.getInput());
            AiResponse response = AiResponse.getIntent(entry.getInput());

            log.info("KI response Intent: {}", response.getIntent());

            log.info("KI response Confidence level: {}", response.getConfidence());
            String answer = response.isFallback() ? "KI did not detect a clear Intent, so ALL OPTIONS are shown" : "KI detected an Intent";
            log.info("KI response: {}", answer);

            if (entry.isFallbackExpected()) {

                log.info("KI did not detected Intent, giving options");
                assertThat(response.isFallback())
                        .as("Expected fallback response to list options, but got a detected intent")
                        .isTrue();

            } else {

                log.info("Intent detected: {}, expected Intent: {}  ", response.getIntent(), entry.getExpectedIntent());
                assertThat(response.getIntent()).isEqualTo(entry.getExpectedIntent());

                assertThat(response.getConfidence()).isGreaterThan(entry.getExpectedConfidence());
            }
            printIntentOptions(response.getIntent());
        }

    }


    public void printIntentOptions(IntentType intentType) {
        log.info("--------------------------------------------------");
        if (intentType.getIntentId() != 10 && intentType.getIntentId() != 11) {
            log.info("Do you want to apply for: " + intentType.getIntentMessage() + "? If yes, please click the button below");

        } else {
            log.info("Sorry, we could not detect a clear child support intent from your input. Please choose from the following child support options.");
            if (intentType.getIntentId() == 10) {
                log.info("Do you want to apply for: " + intentType.getIntentMessage());
            } else {
                log.info("Application " + intentType.getIntentMessage());
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Closing Test");
    }
}
