package com.verimi.testcommon.framework.utils.gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GsonUtilities {

    private static final Gson gson = new Gson();
    private static final Gson gsonWithNulls = new GsonBuilder().serializeNulls().create();

    public static <T> T jsonFileToObject(Type type, String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException ex) {
            log.error("Can't read json from file", ex);
        }
        return null;
    }

    public static <T> T jsonStringToObject(Type type, String jsonString) {
        return gson.fromJson(jsonString, type);
    }

    public static <T> String objectToJsonWithNulls(T object) {
        return gsonWithNulls.toJson(object);
    }
    public static <T> String objectToJson(T object) {
        return gson.toJson(object);
    }
}
