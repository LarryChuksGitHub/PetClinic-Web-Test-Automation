package com.verimi.testcommon.framework.utils.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

/**
 *  Think twice if you're using this class. You need to parse your response to object
 *  using method .as(YourClass.class) instead of using this class
 */
@Slf4j
public class JsonUtil {

    /**
     *  Think twice if you're using this method. You need to parse your response to object
     *  using method .as(YourClass.class) instead of doing this
     */
    public static String getJsonValue(String json, String element) {
        JsonElement jsonElement = JsonParser.parseString(json).getAsJsonObject().get(element);
        if (jsonElement instanceof JsonNull) {
            return "null";
        } else {
            return JsonParser.parseString(json).getAsJsonObject().get(element).getAsString();
        }
    }

    public static JsonObject parseJson(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    /**
     *  Think twice if you're using this method. You need to parse your response to object
     *  using method .as(YourClass.class) instead of doing this
     */
    public static String getValueFromResponse(Response response, String attribute) {
        return response.then().extract().jsonPath().getString(attribute);
    }

    public static List<String> getJsonKeys(JSONObject jsonObject) {
        List<String> keys = new ArrayList<>();
        jsonObject.keys().forEachRemaining(key -> keys.add(key));
        return keys;
    }


    public static List<String> getJsonValueFromPageSource(String pageSource, String key) {
        log.info("Extracting JSON values for key '{}' from page source", key);
        String jsonPart = extractJsonFromPreTag(pageSource);
        if (jsonPart.isEmpty()) {
            log.warn("No JSON <pre> block found in page source for key '{}'", key);
            return Collections.emptyList();
        }

        List<String> jsonValues = extractValuesForKeys(jsonPart, key);
        log.info("Extracted JSON values for key '{}': {}", key, jsonValues);
        return jsonValues;
    }


    private static String extractJsonFromPreTag(String pageSource) {
        log.info("Extracting JSON part between <pre> tags to simplify matching");
        Pattern prePattern = Pattern.compile("<pre>(.*?)</pre>", Pattern.DOTALL);
        Matcher preMatcher = prePattern.matcher(pageSource);

        if (preMatcher.find()) {
            return preMatcher.group(1);
        }
        return "";
    }

    private static List<String> extractValuesForKeys(String jsonPart, String key) {
        log.info("Extracting values for key '{}' from JSON string", key);
        Pattern keyPattern = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"(.*?)\"");
        Matcher keyMatcher = keyPattern.matcher(jsonPart);

        List<String> values = new ArrayList<>();
        while (keyMatcher.find()) {
            values.add(keyMatcher.group(1));
        }
        return values;
    }


}
