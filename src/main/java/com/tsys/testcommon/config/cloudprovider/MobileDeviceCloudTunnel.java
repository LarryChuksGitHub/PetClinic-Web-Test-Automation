package com.tsys.testcommon.config.cloudprovider;

import static com.tsys.testcommon.config.hooks.Hooks.isMobilePlatform;
import static com.tsys.testcommon.framework.utils.timeout.Timeout.MOBILE_DEVICE_CLOUD_IDLE_TIMEOUT;
import static java.lang.String.format;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONException;

import com.browserstack.local.Local;
import com.tsys.testcommon.framework.utils.sleep.SleepUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MobileDeviceCloudTunnel extends CloudTunnel {

    protected String localIdentifier;
    protected Local bsLocal;

    @Override
    public void startTunnel(final Map<String, Object> cloudProviderOptions) throws Exception {
        bsLocal = new Local();
        localIdentifier = UUID.randomUUID().toString();

        setMobileDeviceCloudOptions(cloudProviderOptions, localIdentifier);
        Map<String, String> bsLocalArgs = new HashMap<>();
        bsLocalArgs.put("key", CloudProvider.getInstance().getKey());
        bsLocalArgs.put("localIdentifier", localIdentifier);

        // Due to multiple tests executing in parallel on GitLab
        // multiple thread/process are trying to write to the same directory and file,
        // and this causes 'Text file busy' Exception.
        // To overcome this issue binary path is explicitly specified
        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.contains("Mac")) {
            bsLocalArgs.put("binarypath", new File("browserstack/mac/BrowserStackLocal").getAbsolutePath());
         String fileString =   new File("browserstack/mac/BrowserStackLocal").getAbsolutePath();
        } else if (operatingSystem.toLowerCase().contains("linux")) {
            bsLocalArgs.put("binarypath", new File("mobiledevicecloud/linux/BrowserStackLocal").getAbsolutePath());
        } else {
            bsLocalArgs.put("binarypath", new File("mobiledevicecloud/windows/BrowserStackLocal.exe").getAbsolutePath());
        }
        bsLocalArgs.put("forcelocal", "true");
        bsLocalArgs.put("verbose", "1");
        try {
            bsLocal.start(bsLocalArgs);
        } catch (JSONException jsonException) {
            log.warn("Caught JSONException while starting mobile device cloud local is not stable. Trying again. ", jsonException);
            SleepUtil.sleep(1000);
            bsLocal.start(bsLocalArgs);
        }
        if (!bsLocal.isRunning()) {
            throw new IllegalStateException("Failed to start Started mobile device cloud local!");
        } else {
            log.info("Started mobile device cloud local with identifier: {}", localIdentifier);
        }

    }

    @Override
    public void stopTunnel() {
        try {
            bsLocal.stop();
            if (bsLocal.isRunning()) {
                throw new IllegalStateException(format("Unable to stop Started mobile device cloud started with local identifier: %s",
                        localIdentifier));
            } else {
                log.info("Stopped mobile device cloud which was started with local identifier: {}", localIdentifier);
            }
        } catch (Exception e) {
            log.error("Error stopping mobile device cloud Local: ", e);
        }
    }

    private void setMobileDeviceCloudOptions(Map<String, Object> mobileDeviceCloudOptions, String localIdentifier) {
        mobileDeviceCloudOptions.put("local", "true");
        mobileDeviceCloudOptions.put("localIdentifier", localIdentifier);
        mobileDeviceCloudOptions.put("idleTimeout", MOBILE_DEVICE_CLOUD_IDLE_TIMEOUT);
        if (!isMobilePlatform()) {
            mobileDeviceCloudOptions.put("networkLogs", "true");
        }
    }

}
