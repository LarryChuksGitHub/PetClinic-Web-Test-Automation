package com.petclinic.testcommon.framework.email;

import static io.restassured.RestAssured.given;
import static java.net.URI.create;

import java.util.List;
import java.util.Map;

import io.restassured.path.json.JsonPath;

public class Email {

    public static final String MAIL_HOG_ENDPOINT = "/api/v2/search";

    public static List<Map> getEmailsForAddress(String mailHogUrl, String email) {
        JsonPath jsonPath = given().param("kind", "to")
                .param("query", email)
                .header("HOST", create(mailHogUrl).getHost())
                .when()
                .get(mailHogUrl + MAIL_HOG_ENDPOINT)
                .getBody()
                .jsonPath();
        return jsonPath.getList("items", Map.class);
    }

    public static List<Map> getEmailsForAddress1(String mailHogUrl, String email) {
        JsonPath jsonPath = given().param("kind", "to")
                .param("query", email)
                .header("HOST", create(mailHogUrl).getHost())
                .when()
                .get(mailHogUrl)
                .getBody()
                .jsonPath();
        return jsonPath.getList("items", Map.class);
    }


    public static List<Map> getAllEmails(String mailHogUrl) {
        JsonPath jsonPath = given()
                .header("HOST", create(mailHogUrl).getHost())
                .when()
                .get(mailHogUrl)
                .getBody()
                .jsonPath();
        return jsonPath.getList("items", Map.class);
    }
}
