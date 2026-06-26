package com.verimi.testcommon.endpointactions.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Accessors(chain = true)
@Data
public class AiResponse {
    private IntentType intent;
    private double confidence;
    private boolean isFallback;

    public static AiResponse getIntent(String input) {
        input = input.toLowerCase().replace(" ", "");
        if ((input.contains("baby") && input.contains("neulichumgezogen"))
                || (input.contains("volljährigeKind") && input.contains("wohnunganmelden"))
                || (input.contains("wohnung") && input.contains("babies"))
                || (input.contains("wohnung") && input.contains("neulichumgezogen"))
                || (input.contains("apartment") && input.contains("babies"))
                || (input.contains("flat") && input.contains("babies"))
                || (input.contains("flat") && input.contains("grownup"))
                || input.contains("newbornchild") && input.contains("volljährigeKind")) {
            return AiResponse
                    .builder()
                    .intent(IntentType.LIST_ALL_INTENT_OPTIONS)
                    .confidence(0.0)
                    .isFallback(true)
                    .build();

        } else if (input.contains("baby")
                || input.contains("neugeborenes")
                || input.contains("kindbekommen")
                || input.contains("kinderbekommen")
                || input.contains("kindergldfürsneugeborenes")
                || input.contains("babby")
                || input.contains("babies")
                || input.contains("Kindergldbgeburt")
                || input.contains("geburt")
                || input.contains("Kindergeldabgeburt")
                || input.contains("newbornchild")
                || input.contains("newborn")) {
            return AiResponse
                    .builder()
                    .intent(IntentType.CHILD_SUPPORT_FROM_BIRTH)
                    .confidence(0.98)
                    .isFallback(false)
                    .build();
        } else if (input.contains("volljährig")
                || input.contains("adult")
                || input.contains("grownup")
                || input.contains("Kindergldbvolljährig")
                || input.contains("Kindergeldbvolljährig")
                || input.contains("volljährige")) {
            return AiResponse
                    .builder()
                    .intent(IntentType.CHILD_SUPPORT_FOR_ADULT)
                    .confidence(0.96)
                    .isFallback(false)
                    .build();

        } else if (input.contains("kind")
                || input.contains("kinder")
                || input.contains("child")
                || input.contains("children")) {
            return AiResponse
                    .builder()
                    .intent(IntentType.CHILD_SUPPORT_FROM_BIRTH_OR_AS_ADULT)
                    .confidence(0.65)
                    .isFallback(true)
                    .build();

        } else if (input.contains("umgezogen")
                || input.contains("wohnunganmelden")
                || input.contains("apartment")
                || input.contains("flat")
                || input.contains("adresseändern")
                || input.contains("house")
                || input.contains("home")
                || input.contains("newhouseregistration")
                || input.contains("wohnung")
                || input.contains("newhouse")
                || input.contains("wohnunganmelden")) {
            return AiResponse
                    .builder()
                    .intent(IntentType.HOUSE_REGISTRATION)
                    // .intent(IntentType.CHILD_SUPPORT_FROM_BIRTH)
                    .confidence(0.97)
                    .isFallback(false)
                    .build();

        }
        return AiResponse
                .builder()
                .intent(IntentType.LIST_ALL_INTENT_OPTIONS)
                .confidence(0.0)
                .isFallback(true)
                .build();
    }

}