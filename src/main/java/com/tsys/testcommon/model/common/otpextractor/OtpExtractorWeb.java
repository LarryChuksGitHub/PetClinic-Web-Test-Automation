package com.tsys.testcommon.model.common.otpextractor;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OtpExtractorWeb {

    /**
     * Opens the inbox, finds the email matching recipient and subject,
     * extracts the OTP from the email body and returns it.
     */
    public static String getOtpCode(
            WebDriver driver,
            String inboxUrl,
            String emailAddress,
            String subject) {

        driver.get(inboxUrl);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Adjust locator according to the inbox implementation
        java.util.List<WebElement> emails =
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(".email-row")));

        for (WebElement email : emails) {

            String recipient =
                    email.findElement(By.cssSelector(".recipient")).getText();

            String emailSubject =
                    email.findElement(By.cssSelector(".subject")).getText();

            if (recipient.equalsIgnoreCase(emailAddress)
                    && emailSubject.equalsIgnoreCase(subject)) {

                email.click();

                WebElement bodyElement =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".email-body")));

                String body = bodyElement.getText();

                return extractOtp(body);
            }
        }

        throw new NoSuchElementException(
                "No email found for recipient="
                        + emailAddress
                        + ", subject="
                        + subject);
    }

    private static String extractOtp(String emailBody) {

        // Matches 6-digit OTP codes
        Pattern pattern = Pattern.compile("\\b(\\d{6})\\b");

        Matcher matcher = pattern.matcher(emailBody);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new RuntimeException(
                "OTP code not found in email body: " + emailBody);
    }
}