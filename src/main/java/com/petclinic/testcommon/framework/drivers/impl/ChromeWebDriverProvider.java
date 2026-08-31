package com.petclinic.testcommon.framework.drivers.impl;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.petclinic.testcommon.config.cloudprovider.CloudProvider;
import com.petclinic.testcommon.framework.drivers.BaseRemoteWebDriverProvider;
import com.petclinic.testcommon.framework.drivers.LocalWebDriverProvider;
import com.petclinic.testcommon.framework.drivers.RemoteWebDriverProvider;
import com.petclinic.testcommon.framework.utils.files.FileUtils;
import com.petclinic.testcommon.framework.utils.timeout.Timeout;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChromeWebDriverProvider extends BaseRemoteWebDriverProvider
        implements LocalWebDriverProvider, RemoteWebDriverProvider {

    // ── UA for headless to look like desktop ───────────────────────────────────
    private static final String HEADLESS_DESKTOP_UA =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/129.0.0.0 Safari/537.36";

    // ── ZAP toggle ─────────────────────────────────────────────────────────────
    private static final String ZAP_PROP       = "zap-test";
    private static final String ZAP_ENABLED    = "yes";
    private static final String ZAP_PROXY_ADDR = "127.0.0.1:8080";

    // ── Pref keys ──────────────────────────────────────────────────────────────
    private static final String CAP_DL_DIR     = "download.default_directory";
    private static final String CAP_AUTO_DL    = "profile.content_settings.exceptions.automatic_downloads.*.setting";
    private static final String CAP_CRED_SVC   = "credentials_enable_service";
    private static final String CAP_PW_MANAGER = "profile.password_manager_enabled";
    private static final String CAP_PW_LEAK    = "profile.password_manager_leak_detection";

    // ── Flag sets ──────────────────────────────────────────────────────────────
    private static final String[] BASE_FLAGS = {
            "--disable-gpu",
            "--use-fake-device-for-media-stream",
            "--use-fake-ui-for-media-stream",
            "--window-size=1920,1080",
            "--window-position=0,0",
            "--ignore-certificate-errors",
            "--no-first-run",
            "--no-default-browser-check"
    };

    private static final String[] CI_SAFE_FLAGS = {
            "--no-sandbox",
            "--disable-dev-shm-usage"
    };

    // Extra stabilizers for headless click/scroll behavior
    private static final String[] HEADLESS_HARDENERS = {
            "--force-device-scale-factor=1",
            "--disable-smooth-scrolling"
    };

    private Consumer<ChromeOptions> customChromeOptions;

    public ChromeWebDriverProvider() {
        super(null);
    }

    public ChromeWebDriverProvider(String methodName) {
        super(methodName);
    }

    @Override
    public RemoteWebDriver start() {
        boolean browserStackRun = CloudProvider.isCloudRun();
        validateConfig(browserStackRun, false);

        if (browserStackRun) {
            return startBrowserStack();
        }

        return startLocal();
    }

    @Override
     protected String getBrowser() {
        return "chrome";
    }

    @Override
    protected DesiredCapabilities getCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, buildChromeOptions());
        applyCustomCapabilities(caps);
        return caps;
    }

    // -------------------------------------------------------------------------
    // main start branches
    // -------------------------------------------------------------------------

    private RemoteWebDriver startBrowserStack() {
        try {
            log.info("BrowserStack requested → delegating to startRemote()");
            return startRemote();
        } catch (Exception e) {
            throw new RuntimeException("Failed to start remote BrowserStack session", e);
        }
    }

    private RemoteWebDriver startLocal() {
        ChromeOptions options = buildChromeOptions();
        RemoteWebDriver driver = new ChromeDriver(options);

        if (false) {
            try {
                driver.manage().window().maximize();
            } catch (Exception ignore) {
                // Some environments can't maximize — ignore
            }
        }

        driver.manage()
                .timeouts()
                .pageLoadTimeout(Duration.ofSeconds(Timeout.DEFAULT_PAGE_LOAD_TIME));

        return driver;
    }

    // -------------------------------------------------------------------------
    // options building
    // -------------------------------------------------------------------------

    private ChromeOptions buildChromeOptions() {
        boolean browserStackRun = CloudProvider.isCloudRun();

        ChromeOptions options = createBaseOptions();
        applyHeadlessIfNeeded(options);
        applyLocalFlagsIfNeeded(options, browserStackRun);
        applyPrefs(options, browserStackRun);
        applyZapIfEnabled(options);
        options.setAcceptInsecureCerts(true);

        applyCustomOptions(options);

        // version-safe debug in local + CI
        try {
            Map<String, Object> optionsMap = options.asMap();
            log.info("ChromeOptions (asMap): {}", optionsMap);
            Object args = optionsMap.get("args");
            if (args != null) {
                log.info("Chrome arguments: {}", args);
            }
            Object prefs = optionsMap.get("prefs");
            if (prefs != null) {
                log.info("Chrome prefs: {}", prefs);
            }
        } catch (Exception e) {
            log.warn("Could not log ChromeOptions details: {}", e.getMessage());
        }

        return options;
    }

    private ChromeOptions createBaseOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(BASE_FLAGS);
        return options;
    }

    private void applyHeadlessIfNeeded(ChromeOptions options) {
        if (true) {
            return;
        }
        options.addArguments("--headless=new");
        options.addArguments(HEADLESS_HARDENERS);

        boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));
        if (!isCI) {
            options.addArguments("--user-agent=" + HEADLESS_DESKTOP_UA);
            log.info("Chrome running in HEADLESS mode (local) → applying desktop UA");
        } else {
            log.info("Chrome running in HEADLESS mode (CI) → NOT applying desktop UA");
        }
    }

    private void applyLocalFlagsIfNeeded(ChromeOptions options, boolean browserStackRun) {
        if (browserStackRun) {
            return;
        }
        options.addArguments(CI_SAFE_FLAGS);
    }

    private void applyPrefs(ChromeOptions options, boolean browserStackRun) {
        options.setExperimentalOption("prefs", buildPrefs(browserStackRun));
    }

    private void applyZapIfEnabled(ChromeOptions options) {
        boolean zapEnabled = ZAP_ENABLED.equals(System.getProperty(ZAP_PROP, "no"));
        if (!zapEnabled) {
            return;
        }

        Proxy zap = new Proxy()
                .setHttpProxy(ZAP_PROXY_ADDR)
                .setSslProxy(ZAP_PROXY_ADDR);

        options.setCapability(CapabilityType.PROXY, zap);
        log.info("ZAP proxy enabled at {}", ZAP_PROXY_ADDR);
    }

    private Map<String, Object> buildPrefs(boolean browserStack) {
        Map<String, Object> prefs = new HashMap<>();

        if (!browserStack) {
            prefs.put(CAP_DL_DIR, new FileUtils().getDownloadFolderPath().toString());
        }

        prefs.put(CAP_AUTO_DL, 1);
        prefs.put(CAP_CRED_SVC, false);
        prefs.put(CAP_PW_MANAGER, false);
        prefs.put(CAP_PW_LEAK, false);

        return prefs;
    }

    // -------------------------------------------------------------------------
    // helpers
    // -------------------------------------------------------------------------

    private void validateConfig(boolean browserStackRun, boolean headless) {
        if (browserStackRun && headless) {
            throw new IllegalStateException(
                    "Invalid configuration: BROWSERSTACK=true and HEADLESS_MODE=true. " +
                            "Set HEADLESS_MODE=false for BrowserStack, or BROWSERSTACK=false for local headless.");
        }
    }

    public ChromeWebDriverProvider setCustomChromeOptions(Consumer<ChromeOptions> customChromeOptions) {
        this.customChromeOptions = customChromeOptions;
        return this;
    }

    private void applyCustomOptions(ChromeOptions chromeOptions) {
        if (customChromeOptions != null) {
            customChromeOptions.accept(chromeOptions);
        }
    }
}
