package com.tsys.testcommon.model.common.otpextractor;

import static com.tsys.testcommon.config.Config.LOAD_WAIT;
import static com.tsys.testcommon.config.Config.isAndroid;
import static com.tsys.testcommon.pageobject.mobile.MobileScreen.ANDROID_CHROME_PACKAGE;
import static com.tsys.testcommon.pageobject.mobile.MobileScreen.IOS_SAFARI_PACKAGE;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OtpExtractor {

    private static final Pattern OTP_PATTERN = Pattern.compile("\\b(\\d{6})\\b");

    @SneakyThrows
    public static String getOtpCode(
            WebDriver driver,
            String inboxUrl,
            String emailAddress,
            String expectedSubject) {

        openMobileBrowser(driver);

        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            //  try {
            //    androidDriver.get(inboxUrl);


            WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(LOAD_WAIT));


            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("com.android.chrome:id/search_box_text"))).sendKeys(inboxUrl);
            androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
            //androidDriver.pressKeyCode(66);
            //Enter key
            // accept Alter
            // androidDriver.findElement(By.id("com.android.chrome:id/search_box_text")).click();
            //  Alert alert = androidDriver.switchTo().alert(); //com.android.chrome:id/positive_button
            //  alert.accept();
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("com.android.chrome:id/positive_button"))).click();

            androidDriver.rotate(ScreenOrientation.LANDSCAPE);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@text='" + emailAddress + "']")));

            androidDriver.findElement(By.xpath("//*[@text='" + emailAddress + "']")).click();
            androidDriver.rotate(ScreenOrientation.PORTRAIT);
            //wait.until(ExpectedConditions.visibilityOfElementLocated(
            //  By.xpath("//android.widget.FrameLayout[@content-desc=\"Web View\"]")));

            /*
                List<WebElement> rows =
                        driver.findElements(By.xpath("//android.widget.FrameLayout[@content-desc=\"Web View\"]"));

                */
            // for (WebElement row : rows) {

            //  String rowText = row.getText();

            //  if (rowText.contains(emailAddress)
            //         && rowText.contains(expectedSubject)) {

            //    row.click();

/*

                        WebElement bodyFrame = wait.until(
                                ExpectedConditions.visibilityOfElementLocated(
                                        By.id("preview-html")));

                        driver.switchTo().frame(bodyFrame);

                        String emailBody =
                                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                                By.tagName("body")))
                                        .getText();

                        androidDriver.switchTo().defaultContent();

 */
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@text='Ihr Bestaetigungscode fuer die KIVP-App']")));
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(20)); // Wait for the email content to load

            // Matcher matcher = OTP_PATTERN.matcher(emailBody); // Nachweis hinzufügen
            String pageSource = androidDriver.getPageSource();
            Matcher matcher = OTP_PATTERN.matcher(pageSource);


            if (matcher.find()) {
                log.info("OTP: " + matcher.group(1));
                return matcher.group(1);
            } else {
                return null;
            }

            // }
            // }

            //   } catch (WebDriverException e) {
            //      androidDriver.activateApp(ANDROID_CHROME_PACKAGE);
            // }

        } else {
            IOSDriver iosDriver = ((IOSDriver) driver);

            iosDriver.get(inboxUrl);

            WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(30));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("table tbody tr")));

            List<WebElement> rows =
                    driver.findElements(By.cssSelector("table tbody tr"));

            for (WebElement row : rows) {

                String rowText = row.getText();

                if (rowText.contains(emailAddress)
                        && rowText.contains(expectedSubject)) {

                    row.click();

                    WebElement bodyFrame = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("preview-html")));

                    driver.switchTo().frame(bodyFrame);

                    String emailBody =
                            wait.until(ExpectedConditions.visibilityOfElementLocated(
                                            By.tagName("body")))
                                    .getText();

                    iosDriver.switchTo().defaultContent();

                    Matcher matcher = OTP_PATTERN.matcher(emailBody);

                    if (matcher.find()) {
                        return matcher.group(1);
                    }

                    throw new RuntimeException(
                            "OTP not found in email body.");
                }
            }

            return null;
        }
    }


    public static void openMobileBrowser(WebDriver driver) {
        if (isAndroid()) {
            AndroidDriver androidDriver = ((AndroidDriver) driver);
            try {
                androidDriver.activateApp(ANDROID_CHROME_PACKAGE);
            } catch (WebDriverException e) {
                androidDriver.activateApp(ANDROID_CHROME_PACKAGE);
            }
            androidDriver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            IOSDriver iosDriver = ((IOSDriver) driver);
            iosDriver.activateApp(IOS_SAFARI_PACKAGE);
        }

    }

}