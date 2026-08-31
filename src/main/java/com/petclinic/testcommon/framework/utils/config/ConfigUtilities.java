package com.petclinic.testcommon.framework.utils.config;

import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.petclinic.testcommon.framework.utils.files.FileUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigUtilities {

    public static final String ENV_VARIABLE_OF_TEST_ENV = "TEST_ENV";
    public static final String DEV = "dev";
    public static final String STAGING = "staging";

    private static final String[] VALID_PROP_FILE_NAMES = {DEV, STAGING};

    public static Properties loadProperties() {
        String testEnvironmentName = validateEnvironmentVariable(System.getenv(ENV_VARIABLE_OF_TEST_ENV));
        String propertiesFileName = testEnvironmentName + "-config.properties";
        return FileUtils.loadProperties(propertiesFileName);
    }

    public static Properties loadProperties(String env) {
        String testEnvironmentName = validateEnvironmentVariable(env);
        String propertiesFileName = testEnvironmentName + "-config.properties";
        return FileUtils.loadProperties(propertiesFileName);
    }

    public static boolean isCurrentEnvironment(String environment) {
        return System.getenv(ENV_VARIABLE_OF_TEST_ENV).equalsIgnoreCase(environment);
    }

    public static String getCurrentEnvironment() {
        return System.getenv(ENV_VARIABLE_OF_TEST_ENV);
    }

    private static String validateEnvironmentVariable(String testEnvironment) {
        String validPropFileNamesText = StringUtils.join(VALID_PROP_FILE_NAMES, ", ");
        if (null == testEnvironment || testEnvironment.isEmpty()) {
            log.error("Missing environment variable: {} (possible values: {})",
                    testEnvironment, validPropFileNamesText);
            System.exit(1);
        } else if (!ArrayUtils.contains(VALID_PROP_FILE_NAMES, testEnvironment)) {
            log.error("The environment: {}, is not in the common configuration: Possible values: {}). " +
                            "Using an environment that is not in the configuration may cause issues!",
                    testEnvironment, VALID_PROP_FILE_NAMES);
            throw new IllegalStateException("Unknown env: " + testEnvironment);
        }
        return testEnvironment;
    }
}
