package com.tsys.testcommon.framework.utils.gson;

import com.google.gson.GsonBuilder;

/**
 * Use this class to serialize/deserialize objects from JSON instead of extending from @CreateJSONBody class.
 * You can also add your custom serializers/deserializers to work with LocalDate or any other class
 */
public class Gson {

    public static String toJson(Object object) {
        return new GsonBuilder()
                .create()
                .toJson(object);
    }

    /**
     * This method deserialize from JSON and return object of specified type
     */
    public static <T> T fromJson(String json, Class<T> klass) {
        return new GsonBuilder()
                .create()
                .fromJson(json, klass);
    }

}
