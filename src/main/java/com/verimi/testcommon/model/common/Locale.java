package com.verimi.testcommon.model.common;

import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.NotImplementedException;

public class Locale {

    public static final String de_DE = "de";
    public static final String en_UK = "en";
    private static String locale;

    public static String getLocale() {
        return locale;
    }

    public static DateTimeFormatter getDateFormat() {
        return switch (getLocale()) {
            case (de_DE) -> DateTimeFormatter.ofPattern("dd.MM.yyyy");
            case (en_UK) -> DateTimeFormatter.ofPattern("yyyy-MM-dd");
            default -> throw new NotImplementedException("Add proper format for your new locale to Locale.class");
        };
    }

    public static void setLocale(String locale) {
        Locale.locale = locale;
    }
}