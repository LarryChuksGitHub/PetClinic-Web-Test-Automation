package com.tsys.testcommon.model.common;

import static com.tsys.testcommon.framework.utils.files.FileUtils.loadProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class MessageConstants {

    private static final String MESSAGES = "/messages.properties";
    private static final Map<String, Properties> PROP;


    static {
        PROP = new HashMap<>();
        PROP.put(Locale.de_DE, loadProperties("locale/" + Locale.de_DE + MESSAGES));
        PROP.put(Locale.en_UK, loadProperties("locale/" + Locale.en_UK + MESSAGES));
    }

    public static String getLocalizedMessage(String key, String locale) {
        String result = PROP.get(locale).getProperty(key);
        return (null == result) ? key : result;
    }

    public static String getLocalizedMessage(String key) {
        String result = getLocalizedMessage(key, Locale.getLocale());
        return (null == result) ? key : result;
    }

}
