package com.verimi.testcommon.config.cloudprovider;

import static com.verimi.testcommon.config.hooks.Hooks.isMobilePlatform;
import static com.verimi.testcommon.framework.utils.timeout.Timeout.BROWSERSTACK_IDLE_TIMEOUT;
import static java.lang.String.format;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONException;

import com.browserstack.local.Local;
import com.verimi.testcommon.framework.utils.sleep.SleepUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BrowserstackTunnel extends CloudTunnel {

    protected String localIdentifier;
    protected Local bsLocal;

    @Override
    public void startTunnel(final Map<String, Object> cloudProviderOptions) throws Exception {
        bsLocal = new Local();
        localIdentifier = UUID.randomUUID().toString();

        setBrowserStackOptions(cloudProviderOptions, localIdentifier);
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
        } else if (operatingSystem.toLowerCase().contains("linux")) {
            bsLocalArgs.put("binarypath", new File("browserstack/linux/BrowserStackLocal").getAbsolutePath());
        } else {
            bsLocalArgs.put("binarypath", new File("browserstack/windows/BrowserStackLocal.exe").getAbsolutePath());
        }
        bsLocalArgs.put("forcelocal", "true");
        bsLocalArgs.put("verbose", "1");
        try {
            bsLocal.start(bsLocalArgs);
        } catch (JSONException jsonException) {
            log.warn("Caught JSONException while starting BrowserStackLocal BS is not stable. Trying again. ", jsonException);
            SleepUtil.sleep(1000);
            bsLocal.start(bsLocalArgs);
        }
        if (!bsLocal.isRunning()) {
            throw new IllegalStateException("Failed to start BrowserStackLocal!");
        } else {
            log.info("Started BrowserStackLocal with identifier: {}", localIdentifier);
        }

    }

    @Override
    public void stopTunnel() {
        try {
            bsLocal.stop();
            if (bsLocal.isRunning()) {
                throw new IllegalStateException(format("Unable to stop BrowserStack started with local identifier: %s",
                        localIdentifier));
            } else {
                log.info("Stopped BrowserStack which was started with local identifier: {}", localIdentifier);
            }
        } catch (Exception e) {
            log.error("Error stopping BrowserStack Local: ", e);
        }
    }

    private void setBrowserStackOptions(Map<String, Object> browserstackOptions, String localIdentifier) {
        browserstackOptions.put("local", "true");
        browserstackOptions.put("localIdentifier", localIdentifier);
        browserstackOptions.put("idleTimeout", BROWSERSTACK_IDLE_TIMEOUT);
        if (!isMobilePlatform()) {
            browserstackOptions.put("networkLogs", "true");
        }
    }

}
