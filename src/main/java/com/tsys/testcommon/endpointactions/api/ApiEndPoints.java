package com.tsys.testcommon.endpointactions.api;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_MULTI_STATUS;
import static org.apache.http.HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_PARTIAL_CONTENT;
import static org.apache.http.HttpStatus.SC_RESET_CONTENT;

import java.util.List;

import com.tsys.testcommon.framework.asserts.DippAssertions;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiEndPoints {

    public static void ensureStatusCodeIsAmong200s(Response response) {
        List<Integer> twoHundredsStatusCode = List.of(
                SC_CREATED, SC_NO_CONTENT, SC_OK, SC_RESET_CONTENT, SC_PARTIAL_CONTENT, SC_MULTI_STATUS,
                SC_NON_AUTHORITATIVE_INFORMATION, SC_ACCEPTED);
        DippAssertions dippAssertions = new DippAssertions();
        dippAssertions.assertThat(twoHundredsStatusCode.contains(response.statusCode()))
                .as("Endpoint status code error! Status code should be 200 -series but actual is: " + response.statusCode())
                .isTrue();
    }

}
