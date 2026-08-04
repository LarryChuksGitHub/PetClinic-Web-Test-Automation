package com.tsys.testcommon.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Service {

    POSTGRES("postgres-postgresql-0", "postgres"),
    JWT_SIGNATURE_SERVICE("jwt-signature-service");

    @Getter
    private final String name;

    // Service name may be different in label in different environments
    @Getter
    private final String label;

    Service(String name) {
        this.name = name;
        this.label = name;
    }
}
