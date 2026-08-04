package com.tsys.testcommon.model.common.dataprovider;

import static com.tsys.testcommon.endpointactions.api.IntentType.CHILD_SUPPORT_FROM_BIRTH;
import static com.tsys.testcommon.endpointactions.api.IntentType.CHILD_SUPPORT_FROM_BIRTH_OR_AS_ADULT;

import java.util.ArrayList;
import java.util.List;

import com.tsys.testcommon.endpointactions.api.IntentType;


public class GoldenDatasetGenerator {
    public static List<GoldenDatasetEntry> generateDataset() {

        List<GoldenDatasetEntry> dataset = new ArrayList<>();

        // =========================================
        // CHILD SUPPORT FOR UNDERAGE CHILDREN
        // =========================================

        dataset.add(new GoldenDatasetEntry(
                "Ich habe ein Kind bekommen",
                CHILD_SUPPORT_FROM_BIRTH,
                0.90,
                "de",
                false
        ));

        dataset.add(new GoldenDatasetEntry(
                "Wie beantrage ich Kindergeld?",
                CHILD_SUPPORT_FROM_BIRTH_OR_AS_ADULT,
                0.60,
                "de",
                true
        ));

        dataset.add(new GoldenDatasetEntry(
                "Hab ein Baby bekommen",
                CHILD_SUPPORT_FROM_BIRTH,
                0.90,
                "de",
                false
        ));

        dataset.add(new GoldenDatasetEntry(
                "Kindergld beantragen",
                CHILD_SUPPORT_FROM_BIRTH_OR_AS_ADULT,
                0.60,
                "de",
                true
        ));

        dataset.add(new GoldenDatasetEntry(
                "Kindergld ab Geburt",
                CHILD_SUPPORT_FROM_BIRTH,
                0.90,
                "de",
                false
        ));
        dataset.add(new GoldenDatasetEntry(
                "Kindergeld ab Geburt",
                CHILD_SUPPORT_FROM_BIRTH,
                0.90,
                "de",
                false
        ));


        // =========================================
        // HOUSE REGISTRATION
        // =========================================

        dataset.add(new GoldenDatasetEntry(
                "Ich bin umgezogen",
                IntentType.HOUSE_REGISTRATION,
                0.90,
                "de",
                false
        ));

        dataset.add(new GoldenDatasetEntry(
                "Neue Wohnung anmelden",
                IntentType.HOUSE_REGISTRATION,
                0.92,
                "de",
                false
        ));

        dataset.add(new GoldenDatasetEntry(
                "Adresse ändern",
                IntentType.HOUSE_REGISTRATION,
                0.90,
                "de",
                false
        ));
        // =========================================
        // FALLBACK CASES
        // =========================================

        dataset.add(new GoldenDatasetEntry(
                "Ich brauche Hilfe",
                null,
                0.0,
                "de",
                true
        ));

        dataset.add(new GoldenDatasetEntry(
                "Was muss ich tun?",
                null,
                0.0,
                "de",
                true
        ));

        // =========================================
        // NEGATIVE CASES
        // =========================================

        dataset.add(new GoldenDatasetEntry(
                "@@@@@@@",
                null,
                0.0,
                "de",
                true
        ));

        dataset.add(new GoldenDatasetEntry(
                "",
                null,
                0.0,
                "de",
                true
        ));

        // =========================================
        // MULTI LANGUAGE
        // =========================================

        dataset.add(new GoldenDatasetEntry(
                "I moved to a new apartment",
                IntentType.HOUSE_REGISTRATION,
                0.90,
                "en",
                false
        ));

        dataset.add(new GoldenDatasetEntry(
                "I need child support",
                null,
                0.0,
                "en",
                true
        ));

        dataset.add(new GoldenDatasetEntry(
                "I have a new baby",
                CHILD_SUPPORT_FROM_BIRTH,
                0.90,
                "en",
                false
        ));
        dataset.add(new GoldenDatasetEntry(
                "I have a grown up child",
                IntentType.CHILD_SUPPORT_FOR_ADULT,
                0.90,
                "en",
                false
        ));

        // =========================================
        // SPELLING MISTAKES / BROKEN LANGUAGE
        // =========================================

        dataset.add(new GoldenDatasetEntry(
                "Ich brauche Kindergld für mein Baby",
                CHILD_SUPPORT_FROM_BIRTH,
                0.90,
                "de",
                false
        ));

        dataset.add(new GoldenDatasetEntry(
                "Hab Baby bekommen",
                CHILD_SUPPORT_FROM_BIRTH,
                0.90,
                "de",
                false
        ));

        // =========================================
        // AMBIGUOUS CASES
        // =========================================

        dataset.add(new GoldenDatasetEntry(
                "Ich brauche Hilfe",
                null,
                0.0,
                "de",
                true
        ));

        dataset.add(new GoldenDatasetEntry(
                "Anmelden",
                null,
                0.0,
                "de",
                true
        ));

        // =========================================
        // MULTIPLE INTENTS
        // =========================================

        dataset.add(new GoldenDatasetEntry(
                "Ich habe ein Baby bekommen, ich bin neulich umgezogen, und ich habe ein volljährige Kind",
                IntentType.LIST_ALL_INTENT_OPTIONS,
                0.0,
                "de",
                true
        ));

        return dataset;
    }

}
