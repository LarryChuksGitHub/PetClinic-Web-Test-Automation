package com.verimi.testcommon.framework.drivers;

import static com.verimi.testcommon.config.Config.PLATFORM_NAME;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.verimi.testcommon.config.Config;
import com.verimi.testcommon.config.cloudprovider.CloudProvider;
import com.verimi.testcommon.config.cloudprovider.CloudTunnel;
import com.verimi.testcommon.model.common.Platform;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseRemoteWebDriverProvider extends BaseWebDriverProvider implements RemoteWebDriverProvider {

    protected String methodName;
    protected CloudTunnel cloudTunnel;

    protected abstract String getBrowser();

    protected abstract DesiredCapabilities getCapabilities() throws IOException, JSONException;

    public BaseRemoteWebDriverProvider(String methodName) {
        this.methodName = methodName;
    }

    public void stopCloudTunnel() {
        cloudTunnel.stopTunnel();
    }

    public static String getProjectName() {
        return System.getProperty("projectName", "experimental project");
    }

    public static String getBuildNumber() {
        return System.getProperty("buildNumber", "experimental build");
    }

    @Override
    public RemoteWebDriver startRemote() throws Exception {

        // 1) headless + BS → not allowed
        if (CloudProvider.isCloudRun()) {
            throw new IllegalStateException(
                    "HEADLESS_MODE=true and BROWSERSTACK=true → invalid combination. " +
                            "Set HEADLESS_MODE=false when running on BrowserStack."
            );
        }

        // 2) if BS is actually off, but someone tried to use remote provider
        if (!CloudProvider.isCloudRun()) {
            throw new IllegalStateException(
                    "browserStackRun=false but BaseRemoteWebDriverProvider was used. " +
                            "Enable -DbrowserStackRun=true or use local WebDriver."
            );
        }

        DesiredCapabilities desiredCapabilities = getCapabilities();
        Map<String, Object> cloudProviderOptions = new HashMap<>();

        if (PLATFORM_NAME == Platform.WINDOWS) {
            desiredCapabilities.setCapability("browserName", getBrowser());
        }
        setProjectCapability(cloudProviderOptions, getProjectName() + "_" + Config.CURRENT_ENV, getBuildNumber(), methodName);
        startCloudTunnel(cloudProviderOptions);
        CloudProvider.getInstance().configureBrowserCapabilities(desiredCapabilities, cloudProviderOptions);
        applyCustomCapabilities(desiredCapabilities);

        log.info("Creating WEB Driver..");
        RemoteWebDriver driver = new RemoteWebDriver(new URL(CloudProvider.getInstance().getHubUrl()), desiredCapabilities);
        driver.setFileDetector(new LocalFileDetector());
        driver.manage().window().maximize();
        log.info("[DONE] Creating WEB Driver in cloud...");
        return driver;
    }

    protected void setProjectCapability(final Map<String, Object> cloudProviderOptions,
                                     final String projectName,
                                     final String buildNumber,
                                        final String methodName) {

        CloudProvider.getInstance().configureProjectAttributes(cloudProviderOptions, projectName, buildNumber, methodName);
    }

    public void startCloudTunnel(final Map<String, Object> cloudTunnelOptions) throws Exception {
        cloudTunnel = CloudTunnel.newTunnel();
        cloudTunnel.startTunnel(cloudTunnelOptions);
    }

}
