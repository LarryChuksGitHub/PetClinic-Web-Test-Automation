package com.tsys.testcommon.testhelper;

import static com.tsys.testcommon.model.common.Locale.de_DE;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.tsys.testcommon.model.common.Locale;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseCleanup {
    private static final String[] PHONE_NUMBERS_WITH_COUNTRY_CODE;
    private static final String[] EMAIL_ADDRESSES;
    private static final String PCR;
    private static final String COUNTRY_CODE_SEPARATOR = "-";

    static {
        PHONE_NUMBERS_WITH_COUNTRY_CODE = getPhoneNumbers();
        EMAIL_ADDRESSES = getEmailAddresses();
        PCR = getPcr();
    }

    @BeforeSuite
    @Parameters("locale")
    public void setupSuite(@Optional(de_DE) String locale) {
        Locale.setLocale(locale);
    }

    /**
     * Transforms chain of phone numbers into a list where country code is separated by a hyphen from the rest of the phone number:<br>
     * - Spaces between digits converted to hyphens(-)<br>
     * - Everyting that is not comma (,), hyphen (-) or digits is removed<br>
     * - List items are split by commas<br>
     * @return
     */
    private static String[] getPhoneNumbers() {
        String phoneNumbersWithCountryCode = System.getenv("PHONE_NUMBERS_WITH_COUNTRY_CODE");
        if (phoneNumbersWithCountryCode != null) {
            return phoneNumbersWithCountryCode
                    .replaceAll("(?<=\\d) +(?=\\d)", COUNTRY_CODE_SEPARATOR)
                    .replaceAll("[^\\d,-]", "")
                    .split(",");
        } else {
            return new String[]{""};
        }
    }

    private static int hyphenCounter(String phoneNumber) {
        return phoneNumber.length() - phoneNumber.replace(COUNTRY_CODE_SEPARATOR, "").length();
    }

    private static String hyphenFormatter(String phoneNumber) {
        String[] phoneSplit = phoneNumber.split(COUNTRY_CODE_SEPARATOR);
        phoneSplit[0] = phoneSplit[0] + COUNTRY_CODE_SEPARATOR;
        StringBuilder builder = new StringBuilder();
        for (String s : phoneSplit) {
            builder.append(s);
        }
        String formattedPhone = builder.toString();
        return formattedPhone;
    }

    private static String[] getEmailAddresses() {
        String emailAddresses = System.getenv("EMAIL_ADDRESSES");
        if (emailAddresses != null) {
            return emailAddresses.replace(" ", "").split(",");
        } else {
            return new String[]{""};
        }
    }

    private static String getPcr() {
        String pcr = System.getenv("PCR");
        if (pcr != null) {
            return pcr;
        } else {
            return "";
        }
    }

}
