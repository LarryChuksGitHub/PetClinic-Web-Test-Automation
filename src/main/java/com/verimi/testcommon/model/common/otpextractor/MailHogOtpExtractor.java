package com.verimi.testcommon.model.common.otpextractor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailHogOtpExtractor {
    public static final String MAIL_HOG_ENDPOINT = "/api/v2/search";

    private static final Pattern OTP_PATTERN =
            Pattern.compile("\\b(\\d{6})\\b");

    public static String getOtpCode(
            String mailHogBaseUrl,
            String recipientEmail,
            String expectedSubject)
            throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(mailHogBaseUrl + MAIL_HOG_ENDPOINT))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        int counter = 0;
        while (response.statusCode() != 200 && counter < 4) {
            Thread.sleep(1000);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            counter++;
            log.info(response.statusCode() + " received from MailHog. Retrying... (attempt " + counter + ")");
            log.info(response.body() + " received from MailHog.");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        JsonNode items = root.get("items");

        if (items == null || !items.isArray()) {
            throw new RuntimeException("No messages found in MailHog.");
        }

        for (JsonNode message : items) {

            String subject =
                    message.path("Content")
                            .path("Headers")
                            .path("Subject")
                            .get(0)
                            .asText();

            JsonNode toArray =
                    message.path("Content")
                            .path("Headers")
                            .path("To");

            boolean recipientMatches = false;

            for (JsonNode recipient : toArray) {
                if (recipient.asText().contains(recipientEmail)) {
                    recipientMatches = true;
                    break;
                }
            }

            if (recipientMatches && expectedSubject.equals(subject)) {

                String body = message.path("Content")
                        .path("Body")
                        .asText();

                Matcher matcher = OTP_PATTERN.matcher(body);

                if (matcher.find()) {
                    return matcher.group(1);
                }

                throw new RuntimeException(
                        "Matching email found, but no OTP code detected.");
            }
        }

        throw new RuntimeException(
                "No email found for recipient="
                        + recipientEmail
                        + " and subject="
                        + expectedSubject);
    }
}