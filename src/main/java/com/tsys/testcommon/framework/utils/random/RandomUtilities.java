package com.tsys.testcommon.framework.utils.random;

import java.util.Random;
import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtilities {

    public static final Random RANDOM = new Random();

    public static String generateRandomUID() {
        return UUID.randomUUID().toString();
    }

    public static String T_SYS_GENERATED_DOMAIN = "@tsysgenerated.de";

    public static String generateRandomEmail() {
        return ("testMail-" + UUID.randomUUID().toString().replace("-", "") + "@verimi.com").toLowerCase();
    }

    public static String generateRandomEmail(String domainName) {
        return ("testMail-" + UUID.randomUUID().toString().replace("-", "") + "@" + domainName).toLowerCase();
    }

    public static String generateRandomEmailWithDots() {
        return ("t.e.s.t.M.a.i.l.-" + UUID.randomUUID().toString().replace('-', '.') + "@verimi.com").toLowerCase();
    }

    public static String generateRandomEmailWithPlusAndDot() {
        return generateRandomEmailWithPlusAndDotForGivenDomain("verimi.com");
    }

    public static String generateRandomEmailWithPlusAndDotForGivenDomain(String domain) {
        return ("email+with+plus.and.dots+" + UUID.randomUUID().toString().replace("-", "") + "@" + domain).toLowerCase();
    }


    /**
     * Valid SO device id is 20 digits long string https://verimi.atlassian.net/browse/VD-2526
     *
     * @return SO device Id
     */
    public static String getSoDeviceId() {
        return "0000000000" + (RANDOM.nextInt(1_000_000_000) + 1_000_000_000);
    }

    public static char selectRandomCharFromString(String s) {
        return s.charAt(RANDOM.nextInt(s.length()));
    }

    /**
     * Invalid TAN is 6 digits long string
     *
     * @return Invalid TAN
     */
    public static String generateRandomInvalidTAN() {
        return String.valueOf(RANDOM.nextInt(888887) + 111112);
    }

    public static String generateRandomDocumentNumber(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }
}
