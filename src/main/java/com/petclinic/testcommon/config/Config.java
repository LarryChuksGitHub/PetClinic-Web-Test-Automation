package com.petclinic.testcommon.config;


import static com.petclinic.testcommon.model.common.Platform.WINDOWS;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.petclinic.testcommon.framework.db.DatabaseConfig;
import com.petclinic.testcommon.framework.utils.config.ConfigUtilities;
import com.petclinic.testcommon.model.common.Browser;
import com.petclinic.testcommon.model.common.Locale;
import com.petclinic.testcommon.model.common.Platform;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Config {
    private static final String API_URL_PATTERN;
    private static final String API_BASE_URL;
    public static final String FRONTEND_URL_PATTERN;
    public static final String MOCK_URL;

    public static final String CURRENT_ENV;
    public static final Browser BROWSER;
    public static final String DEVICE_NAME;
    public static final String DEVICE_UDID;
    public static final Platform PLATFORM_NAME;
    public static final String APP_NAME;
    public static final int LOAD_WAIT;
    public static final String MAIL_HOG_URL;

    /**
     * Possible formats:
     * jdbc:postgresql://domain:5432/postgres?user=postgres&password=postgres
     * jdbc:mariadb://root:password@domain:3306/hydra
     * https://test-service.url
     */
    private static final String POSTGRES_URL;
    private static final String POSTGRES_ANALYTICS_DATABASE;
    private static final String POSTGRES_HYDRA_DATABASE;
    private static final String POSTGRES_HYDRA_PROXY_DATABASE;

    public static final String SO_DEVICE_ID;
    public static final String SO_DEVICE_NAME;
    public static final String SO_EXTERNAL_DEVICE_ID;
    public static final String ENV_PROXY;

    private static DatabaseConfig postgresConfig;
    private static DatabaseConfig analyticsDbConfig;
    private static DatabaseConfig hydraDbConfig;
    private static DatabaseConfig hydraProxyDbConfig;

    private static final boolean CI_RUN;

    static {
        Properties prop = ConfigUtilities.loadProperties();
        FRONTEND_URL_PATTERN = prop.getProperty("frontend_url");
        MOCK_URL = prop.getProperty("mock_url");
        MAIL_HOG_URL = prop.getProperty("mail_hog_url");
        API_URL_PATTERN = prop.getProperty("api_url");
        API_BASE_URL = prop.getProperty("api_base_url");

        LOAD_WAIT = Integer.parseInt(prop.getProperty("load_wait"));

        BROWSER = Browser.parse(System.getenv("BROWSER"));
        ENV_PROXY = System.getenv("PROXY");
        CURRENT_ENV = System.getenv("TEST_ENV");

        DEVICE_NAME = System.getenv("DEVICE_NAME");
        DEVICE_UDID = System.getenv("DEVICE_UDID");
        PLATFORM_NAME = Platform.parse(System.getenv().getOrDefault("PLATFORM_NAME", WINDOWS.toString()));
        APP_NAME = System.getenv("APP_NAME");
        CI_RUN =
                "true".equalsIgnoreCase(System.getenv("CI")) ||
                        "true".equalsIgnoreCase(System.getenv("GITLAB_CI")) ||
                        System.getenv("CI_JOB_ID") != null;

        POSTGRES_URL = prop.getProperty("postgres_url");
        POSTGRES_ANALYTICS_DATABASE = prop.getProperty("postgres_analytics_database");
        POSTGRES_HYDRA_DATABASE = prop.getProperty("postgres_hydra_database");
        POSTGRES_HYDRA_PROXY_DATABASE = prop.getProperty("postgres_hydra_proxy_database");

        SO_DEVICE_ID = prop.getProperty("so_device_id");
        SO_DEVICE_NAME = prop.getProperty("device_name");
        SO_EXTERNAL_DEVICE_ID = getExternalDeviceId();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    protected Config() {
        throw new IllegalStateException("Utility class");
    }


    private static String getExternalDeviceId() {
        String externalDeviceId = System.getenv("external_device_id");
        if (externalDeviceId != null) {
            log.info("Loading custom external device ID from environment variable.");
            return externalDeviceId;
        } else {
            return ConfigUtilities.loadProperties().getProperty("external_device_id");
        }
    }

    public static boolean isSmokeTest() {
        String action = System.getenv("ACTION");
        return action != null && action.contains("smoke");
    }

    public static boolean isRegressionTest() {
        String action = System.getenv("ACTION");
        return action != null && action.contains("regression");
    }

    public static boolean isDev() {
        return (ConfigUtilities.getCurrentEnvironment().toLowerCase().contains("dev"));
    }

    public static boolean isStaging() {
        return (ConfigUtilities.getCurrentEnvironment().toLowerCase().contains("staging"));
    }

    public static boolean isBrowserStackRun() {
        return Boolean.parseBoolean(System.getProperty("browserStackRun", "false"));
    }

    public static boolean isMobileDeviceCloudRun() {
        return Boolean.parseBoolean(System.getProperty("mobileDeviceCloudRun", "false"));
    }

    public static String getLanguage() {
        return System.getProperty("lang", Locale.en_UK);
    }


    public static boolean isIOS() {
        return PLATFORM_NAME == Platform.IOS;
    }

    public static boolean isAndroid() {
        return PLATFORM_NAME == Platform.ANDROID;
    }

    public static boolean isIpad() {
        return PLATFORM_NAME == Platform.IPAD;
    }

    public static DatabaseConfig getPostgresDbConfig() {
        if (postgresConfig == null) {
            synchronized (Config.class) {
                postgresConfig = new DatabaseConfig(POSTGRES_URL);
            }
        }
        return postgresConfig;
    }

    public static DatabaseConfig getAnalyticsDbConfig() {
        if (analyticsDbConfig == null) {
            synchronized (Config.class) {
                String config = StringUtils.isEmpty(POSTGRES_ANALYTICS_DATABASE) ? POSTGRES_URL :
                        POSTGRES_ANALYTICS_DATABASE;
                analyticsDbConfig = new DatabaseConfig(config);
            }
        }
        return analyticsDbConfig;
    }

    public static DatabaseConfig getHydraDbConfig() {
        if (hydraDbConfig == null) {
            synchronized (Config.class) {
                String config = StringUtils.isEmpty(POSTGRES_HYDRA_DATABASE) ? POSTGRES_URL :
                        POSTGRES_HYDRA_DATABASE;
                hydraDbConfig = new DatabaseConfig(config);
            }
        }
        return hydraDbConfig;
    }

    public static DatabaseConfig getHydraProxyDbConfig() {
        if (hydraProxyDbConfig == null) {
            synchronized (Config.class) {
                String config = StringUtils.isEmpty(POSTGRES_HYDRA_PROXY_DATABASE) ? POSTGRES_URL :
                        POSTGRES_HYDRA_PROXY_DATABASE;
                hydraProxyDbConfig = new DatabaseConfig(config);
            }
        }
        return hydraProxyDbConfig;
    }

    public static String getPostgresDbConfig(String env) {
        return ConfigUtilities.
                loadProperties(env).
                getProperty("postgres_url");
    }

    public static String getHydraDbConfig(String env) {
        return ConfigUtilities.
                loadProperties(env).
                getProperty("postgres_hydra_database");
    }


    public static boolean isCiRun() {
        return CI_RUN;
    }

    public static String getApiUrl() {
        if (!API_URL_PATTERN.contains("")) {
            return API_URL_PATTERN;
        }
        return API_URL_PATTERN.replace("", "");
    }

    public static String getApiBaseUrl() {
        return API_BASE_URL;
    }

    public static String getFeUrl() {
        if (!FRONTEND_URL_PATTERN.contains("")) {
            return FRONTEND_URL_PATTERN;
        }
        return FRONTEND_URL_PATTERN.replace("", "");
    }

}
