package com.verimi.testcommon.model.backend;

import static org.assertj.core.api.Assertions.assertThat;

public class IntentValidator {
    public void validateIntent(String actual, String expected) {
        assertThat(actual)
                .isEqualTo(expected);
    }
}
