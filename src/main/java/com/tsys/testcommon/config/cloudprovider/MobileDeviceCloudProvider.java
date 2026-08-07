package com.tsys.testcommon.config.cloudprovider;

import static com.tsys.testcommon.config.Config.BROWSER;
import static com.tsys.testcommon.config.Config.PLATFORM_NAME;
import static com.tsys.testcommon.config.Config.isMobileDeviceCloudRun;
import static com.tsys.testcommon.config.hooks.Hooks.isMobilePlatform;
import static com.tsys.testcommon.framework.drivers.impl.IosWebDriverProvider.APP_NAME;
import static com.tsys.testcommon.model.common.Browser.SAFARI_IOS;
import static com.tsys.testcommon.model.common.Platform.IOS;
import static com.tsys.testcommon.model.common.Platform.IPAD;
import static io.restassured.RestAssured.given;
import static org.openqa.selenium.remote.Browser.SAFARI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import com.jayway.jsonpath.JsonPath;
import com.tsys.testcommon.config.hooks.Hooks;
import com.tsys.testcommon.config.hooks.ScenarioManager;
import com.tsys.testcommon.framework.utils.logging.LoggingFilters;
import com.tsys.testcommon.framework.utils.sleep.SleepUtil;
import com.tsys.testcommon.model.common.Locale;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobileDeviceCloudProvider extends CloudProvider {

    public static final String MOBILE_DEVICE_CLOUD_APPS_FOR_CUSTOM_ID_URI = "";
    //for FE tests
    public static final String MOBILE_DEVICE_CLOUD_WEB_SESSION_URI = "";
    // for Mobile tests
    public static final String MOBILE_DEVICE_CLOUD_APP_SESSION_URI = "";
    public static final String MOBILE_DEVICE_CLOUD_MEDIA_UPLOAD_URI = "";

    @Override
    public String uploadMediaFile(final DocumentImageName imageName) {
        final String fileName = FilenameUtils.getName(imageName.toString().toLowerCase() + ".jpg");
        final File file = new File("src/test/resources/images/photoident", fileName);

        return given()
                .auth().preemptive().basic(getUserName(), getKey())
                .multiPart("file", file)
                .multiPart("custom_id", imageName.toString().toLowerCase())
                .post(MOBILE_DEVICE_CLOUD_MEDIA_UPLOAD_URI)
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .response()
                .jsonPath()
                .getString("media_url")
                .split("media://")[1];
    }

    @Override
    public String uploadProcessIdQr(final File file) {
        return given()
                .auth().preemptive().basic(getUserName(), getKey())
                .multiPart("file", file)
                .multiPart("custom_id", DocumentImageName.PROCESS_ID_QR.toString().toLowerCase())
                .post(MOBILE_DEVICE_CLOUD_MEDIA_UPLOAD_URI)
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .response()
                .jsonPath()
                .getString("media_url")
                .split("media://")[1];
    }

    @Override
    public void configureBrowserCapabilities(final DesiredCapabilities desiredCapabilities,
                                             final Map<String, Object> cloudProviderOptions) {
        desiredCapabilities.setCapability("bstack:options", cloudProviderOptions);
    }

    // Well... We don't touch what works
    @Override
    public DesiredCapabilities getIosCapabilities(final String iosDeviceName,
                                                  final String iosDeviceVersion,
                                                  final String appFileExtension) {

        log.info("[SELECTED MOBILE DEVICE].." + iosDeviceName + " version " + iosDeviceVersion);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // Test target is mobile browser
        if (BROWSER == SAFARI_IOS) {
            desiredCapabilities.setCapability(XCUITestOptions.BROWSER_NAME_OPTION, SAFARI.browserName());
            Map<String, Object> mobileDeviceCloudOptions = new HashMap<>();
            mobileDeviceCloudOptions.put("osVersion", iosDeviceVersion);
            mobileDeviceCloudOptions.put("deviceName", iosDeviceName);
            mobileDeviceCloudOptions.put("realMobile", "true");
            mobileDeviceCloudOptions.put("local", "false");
            configureBrowserCapabilities(desiredCapabilities, mobileDeviceCloudOptions);
            // Test target is mobile app
        }
        if (PLATFORM_NAME == IPAD) {
            String appName = DEUTSCHLAND_APP_NAME + appFileExtension;
            String[] localIdentApp = {getAppUrl(appName)};
            desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.OTHER_APPS_OPTION, localIdentApp);
        }
        String appName = APP_NAME.contains(appFileExtension) ? APP_NAME : APP_NAME + appFileExtension;
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.APP_OPTION, getAppUrl(appName));
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.DEVICE_NAME_OPTION, iosDeviceName);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.PLATFORM_VERSION_OPTION, iosDeviceVersion);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + XCUITestOptions.AUTO_ACCEPT_ALERTS_OPTION, Boolean.TRUE);
        desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + "language", Locale.getLocale());

        if(isMobileDeviceCloudRun()) {
            desiredCapabilities.setCapability("digitalai:accessKey", CloudProvider.getInstance().getKey());
            desiredCapabilities.setCapability("digitalai:testName", ScenarioManager.getScenario().getName());
            desiredCapabilities.setCapability(APPIUM_CAPABILITY_PREFIX + UiAutomator2Options.APP_OPTION, CloudProvider.getInstance().getAppUrl(appName));
        }

        return desiredCapabilities;
    }


    @Override
    String getUserName() {
        String user = System.getenv("MOBILE_DEVICE_CLOUD_USER");
        if (user == null) {
            user = "tarun206";
        }
        return user;
    }

    @Override
    public String getKey() {
        final String mobileDeviceCloudKey = System.getenv("MOBILE_DEVICE_CLOUD_KEY");
        if (null != mobileDeviceCloudKey) {
            return mobileDeviceCloudKey;
        } else {
            throw new RuntimeException("MOBILE_DEVICE_CLOUD_KEY environment variable is not set");
        }
    }

    @Override
    public String getHubUrl() {
        return "https://mobiledevicecloud.t-systems-mms.eu/wd/hub";
    }

    private String getMobileDeviceCloudAppDataForCustomId(final String customId) {
        return given()
                .auth().preemptive().basic(getUserName(), getKey())
                .filters(LoggingFilters.getFilters())
                .pathParam("customId", customId)
                .get(MOBILE_DEVICE_CLOUD_APPS_FOR_CUSTOM_ID_URI)
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .response()
                .getBody()
                .asString();
    }

    @Override
    public String getAppId(final String app) throws JSONException {
        String json = null;

        if (app.contains(DEUTSCHLAND_APP_NAME) && CloudProvider.getInstance() instanceof MobileDeviceCloudProvider) {
            json = getMobileDeviceCloudAppDataForCustomId(DEUTSCHLAND_APP_NAME);

        } else {
            log.info("App name is not found: {}", app);
        }

        return getAppIdFromJsonResponse(app, json);
    }

    @Override
    public String getAppUrl(String app) throws JSONException {
        return "cloud:de.bund.bmds.kivp.app.dev";
    }

    @Override
    public String getCloudProviderName() throws JSONException {
        return "TSY Mobile Device Cloud";
    }

    @Override
    public String getNetworkLogs(final String sessionId) {
        return given()
                .auth().preemptive().basic(getUserName(), getKey())
                .filters(LoggingFilters.getFilters())
                .get(MOBILE_DEVICE_CLOUD_WEB_SESSION_URI + sessionId + "/network-logs")
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .response()
                .getBody()
                .asString();
    }

    private String getAutomatonType() {
        return Hooks.isMobilePlatform() ? "app-automate" : "automate";
    }

    private String getSessionUrl(final String sessionId) {
        if (sessionId == null) {
            return null;
        }
        return "https://" + getUserName() + ":" + getKey() +
                "@api.browserstack.com/" + getAutomatonType() + "/sessions/" + sessionId + ".json";
    }

    public void setTestResult(final ITestResult testResult, final String sessionId) throws Exception {
        if (null == sessionId) {
            log.warn("SessionId is null. Skipping setting test result on BrowserStack.");
            return;
        }

        String mobileDeviceCloudSessionURI = getSessionUrl(sessionId);
        URI uri = new URI(mobileDeviceCloudSessionURI);
        HttpPut putRequest = new HttpPut(uri);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        if (testResult.getStatus() == ITestResult.SUCCESS) {
            nameValuePairs.add((new BasicNameValuePair("status", "passed")));
        } else if (testResult.getStatus() == ITestResult.FAILURE) {
            nameValuePairs.add((new BasicNameValuePair("status", "failed")));
            nameValuePairs.add((new BasicNameValuePair("reason", testResult.getThrowable().getMessage())));
        } else if (testResult.getStatus() == ITestResult.SKIP) {
            // BrowserStack supports only completed and error status
            nameValuePairs.add((new BasicNameValuePair("status", "failed")));
            nameValuePairs.add((new BasicNameValuePair("reason", "Test Skipped. " + testResult.getThrowable().getMessage())));
        }
        putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpClientBuilder.create().build().execute(putRequest);
        log.info("Successfully updated test result for sessionId: {}", sessionId);
    }

    @Override
    public void configureProjectAttributes(final Map<String, Object> cloudProviderOptions,
                                           final String projectName,
                                           final String buildNumber,
                                           final String sessionName) {
        if (projectName.isEmpty()) {
            cloudProviderOptions.put("projectName", "Experimental Project");
            cloudProviderOptions.put("buildName", "Experimental Build");
        } else {
            cloudProviderOptions.put("projectName", projectName);
            cloudProviderOptions.put("buildName", buildNumber);
        }
        cloudProviderOptions.put("sessionName", sessionName);
        if (!isMobilePlatform()) {
            cloudProviderOptions.put("os", PLATFORM_NAME == IPAD ? IOS : PLATFORM_NAME);
            cloudProviderOptions.put("osVersion", "10");
        }
    }

    @Override
    public String logReportUrl(final String sessionId) {
        if (sessionId == null) {
            return null;
        }
        String mobileDeviceCloudSessionUrl = getSessionUrl(sessionId);
        HttpGet httpGet = new HttpGet(mobileDeviceCloudSessionUrl);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpResponse response = httpClient.execute(httpGet);
            String responseText = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
            String publicUrl = JsonPath.parse(responseText).read("$.automation_session.public_url").toString();
            log.info("Mobile device cloud report URL: <a href=\"{}\">{}</a>", publicUrl, publicUrl);
            return publicUrl;
        } catch (Exception e) {
            log.error("Error getting mobileDeviceCloud report URL", e);
        }
        return null;
    }

    public String getDeviceLogsUrl(final String sessionId) throws IOException {
        URL mobileDeviceCloudSessionURL = new URL(MOBILE_DEVICE_CLOUD_APP_SESSION_URI + sessionId + ".json");
        String authString = getUserName() + ":" + getKey();
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(authString.getBytes());

        JSONObject mobileDeviceCloudSessionAsJsonObject = getSession(mobileDeviceCloudSessionURL, basicAuth);
        JSONObject automationSession = mobileDeviceCloudSessionAsJsonObject.getJSONObject("automation_session");
        return automationSession.getString("device_logs_url");
    }

    //returns the full info of BS session as JSON
    private @NotNull JSONObject getSession(final URL mobileDeviceCloudSessionURL, final String basicAuth) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) mobileDeviceCloudSessionURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", basicAuth);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return new JSONObject(response.toString());
    }

    //general method that returns the text of a page by passing the URL
    @NotNull
    public String getContentOfPage(final String urlAddress) {
        StringBuilder result = new StringBuilder();

        HttpURLConnection connection = null;
        SleepUtil.sleep(5000); //BS can be slower sometimes, need to wait for the page to be generated
        try {
            URL url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();
            String authString = getUserName() + ":" + getKey();
            String basicAuth = "Basic " + Base64.getEncoder().encodeToString(authString.getBytes());

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                }
            } else {
                log.info("GET request not worked, Response Code: {}", responseCode);
            }
        } catch (IOException e) {
            log.info("Failed to fetch content: {}", e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result.toString();
    }

    //returns the BS session's deviceLog from its page as String
    @NotNull
    public String getDeviceLogs(final String sessionId) throws IOException {
        final String deviceLogURL = getDeviceLogsUrl(sessionId);
        return getContentOfPage(deviceLogURL);
    }

    @SneakyThrows
    private String getAppIdFromJsonResponse(final String app, final String json) {
        JSONArray jsonArray = new JSONArray(json);
        String appId = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String appName = jsonObject.getString("app_name");
            if (appName.contains(app)) {
                appId = jsonObject.getString("app_id");
                break;
            }
        }
        log.info("app: {} and appId: {}", app, appId);
        if (appId == null) {
            throw new IllegalStateException(String.format("apk or ipa files are missing from BrowserStack (BS), please upload it again. BS response %s", json));
        }
        return appId;
    }

}
