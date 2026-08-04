package com.tsys.testcommon.config.cloudprovider;

import org.openqa.selenium.OutputType;

import com.tsys.testcommon.framework.utils.testhelper.TestContext;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowserStackUtil {

    public static void captureFailure(AppiumDriver driver, Scenario scenario) {

        if (scenario.isFailed()) {

            byte[] screenshot =
                    driver.getScreenshotAs(
                            OutputType.BYTES);
            scenario.attach(
                    screenshot,
                    "screenshots/image/png",
                    "Failure Screenshot");
        }
    }

    public static void updateScenarioStatus(
            AppiumDriver driver,
            Scenario scenario) {
        try {
            String status =
                    scenario.isFailed()
                            ? "Failed"
                            : "Passed";

            String reason =
                    scenario.isFailed()
                            ? scenario.getName() + " Test Scenario Failed"
                            : scenario.getName() + " Test Scenario Passed";

            if (scenario.isFailed()) {

                Throwable throwable =
                        TestContext.getLastException();
                TestContext.clear();

                if (throwable != null) {
                    reason = throwable.getClass().getSimpleName()
                            + " "+ throwable.getMessage();
                }
            }
            reason = sanitizeReason(reason);

            log.info("Test scenario marked as {}", status);
            // + "browserstack_executor: {\"action\":\"setSessionStatus\", "
            String browserstackCommand =
                    "{"
                            + "browserstack_executor: {\"action\":\"setSessionStatus\", "
                            + "\"arguments\": {"
                            + "\"status\": \"" + status + "\","
                            + "\"reason\": \"" + reason + "\""
                            + "}"
                            + "}";
            driver.executeScript(browserstackCommand);

        } catch (Exception e) {
            log.error(
                    "Failed to update BrowserStack status",
                    e);
        }
    }

    private static String sanitizeReason(String reason) {

        if (reason == null) {
            return "Unknown failure";
        }

        reason = reason
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", " ")
                .replace("\r", " ")
                .replace("\t", " ");

        if (reason.length() > 351) {
            reason = reason.substring(42, 350);
        }
        return reason;
    }
}


