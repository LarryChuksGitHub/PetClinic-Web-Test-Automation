package com.petclinic.testcommon.framework.email;

import static java.lang.Thread.sleep;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;

import com.google.common.util.concurrent.Uninterruptibles;
import com.petclinic.testcommon.framework.utils.constant.NumericConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailFilter {

    private static final int MAX_RETRY_COUNT = 10;
    private static final int WAIT_TIME_BETWEEN_RETRIES = 1000;

    public static List<Map> filterOnSubject(String mailHogUrl, String emailAddress, String subject) throws InterruptedException {
        return filterOnSubjectEquals(mailHogUrl, emailAddress, subject, false);
    }

    private static List<Map> getFilteredEmails(String mailHogUrl, String emailAddress, String subject, boolean equals) {
        List<Map> result = new ArrayList<>();
        List<Map> emails = Email.getEmailsForAddress(mailHogUrl, emailAddress);

        for (Map email : emails) {
            String emailSubject = getSubject(email);
            if ((equals && emailSubject.equals(subject)) || (!equals && emailSubject.contains(subject))) {
                result.add(email);
            }
        }
        return result;
    }

    public static List<Map> filterOnSubjectEquals(String mailHogUrl, String emailAddress, String subject, boolean equals) throws InterruptedException {
        int retryCount = 0;
        List<Map> result;
        do {
            result = getFilteredEmails(mailHogUrl, emailAddress, subject, equals);
            if (result.isEmpty()) {
                log.info("Retrying... Getting emails for {} with subject '{}' (attempt {})", emailAddress, subject, retryCount + 1);
                Thread.sleep(WAIT_TIME_BETWEEN_RETRIES);
                retryCount++;
            }
        } while (shouldRetryAndWait(result, retryCount));

        return result;
    }

    public static List<Map> getMessageViaSubject(String mailHogUrl, String emailAddress, String subject, boolean equals) throws InterruptedException {
        int retryCount = 0;
        List<Map> result;
        result = getFilteredEmails(mailHogUrl, emailAddress, subject, equals);
        while (result.isEmpty() && retryCount < MAX_RETRY_COUNT) {

            log.info("Retrying... Getting emails for {} with subject '{}' (attempt {})", emailAddress, subject, retryCount + 1);
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(NumericConstants.NUMERIC_1));
            result = getFilteredEmails(mailHogUrl, emailAddress, subject, equals);
            retryCount++;
        }
        return result;
    }

    public static List<Map> filterOnBody(String mailHogUrl, String searchTerm) {
        List<Map> filteredEmail = new ArrayList<>();
        var emails = Email.getAllEmails(mailHogUrl);
        int searchCounter = 0;
        for (var email : emails) {
            var emailMap = (LinkedHashMap) email.get("Content");
            if (doesEmailBodyHaveSearchTerm(searchTerm, emailMap)) {
                filteredEmail.add(email);
            }
            ++searchCounter;
            log.info("Getting e-mails with searchTerm text: '{}' try count {}", searchTerm, searchCounter);
        }
        return filteredEmail;
    }

    private static boolean doesEmailBodyHaveSearchTerm(String searchTerm, LinkedHashMap emailMap) {
        return ((String) (emailMap.get("Body"))).toLowerCase().contains(searchTerm.toLowerCase());
    }

    public static String getSubject(Map<String, Map<String, Map<String, List<String>>>> email) {
        List<String> subjects = email.get("Content").get("Headers").get("Subject");
        if (!subjects.isEmpty()) {
            String subjectDecoded = null;
            try {
                subjectDecoded = subjects.get(0).replace(subjects.get(0), MimeUtility.decodeText(subjects.get(0)));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return subjectDecoded;
        }
        throw new RuntimeException("No subject for email: " + email.get("Content").get("Headers"));
    }

    private static boolean shouldRetryAndWait(List<Map> result, int retryCount) throws InterruptedException {
        if (result.isEmpty() && retryCount < MAX_RETRY_COUNT) {
            sleep(WAIT_TIME_BETWEEN_RETRIES);
            log.info("No matching emails found. Retrying... retry count {}, MAX_RETRY_COUNT: {})", retryCount, MAX_RETRY_COUNT);
            return true;
        } else {
            return false;
        }
    }
}
