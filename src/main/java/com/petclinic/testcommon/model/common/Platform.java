package com.petclinic.testcommon.model.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Platform {
    WINDOWS, ANDROID, IOS, IPAD;

    public static Platform parse(String stringToPlatform) {
        for (Platform platform : Platform.values()) {
            if (platform.name().equalsIgnoreCase(stringToPlatform)) {
                return platform;
            }
        }
        log.warn("Unknown platform '{}'. Setting to default Windows", stringToPlatform);
        return WINDOWS;
    }
}
