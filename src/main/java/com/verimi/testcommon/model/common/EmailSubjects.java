package com.verimi.testcommon.model.common;

import static com.verimi.testcommon.framework.utils.files.FileUtils.loadProperties;
import static com.verimi.testcommon.model.common.Locale.getLocale;

import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailSubjects {

    public static final String ACCOUNT_LOCKED_SUBJECT;
    private static final String SUBJECTS = "/email-subjects.properties";
    private static final Properties PROP;

    static {
        PROP = loadProperties("locale/" + getLocale() + SUBJECTS);
        ACCOUNT_LOCKED_SUBJECT = "ACCOUNT_LOCKED_SUBJECT";
    }

    private final String text;

    EmailSubjects(String subject) {
        text = subject;
    }

    public static String getLocalizedEmailSubject(String key) {
        String result = PROP.getProperty(key);
        return (null == result) ? key : result;
    }

    public String getText() {
        return this.text;
    }
}
