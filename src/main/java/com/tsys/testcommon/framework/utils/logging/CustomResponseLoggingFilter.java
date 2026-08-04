package com.tsys.testcommon.framework.utils.logging;

import io.restassured.filter.FilterContext;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomResponseLoggingFilter extends ResponseLoggingFilter {

    public static final int LONG_RESPONSE_BODY_BYTES = 256;

    public CustomResponseLoggingFilter() {
        super();
    }

    private static boolean isMustBeTrimmed(final String responseBody) {
        if (responseBody.length() < LONG_RESPONSE_BODY_BYTES) {
            return false;
        }
        return !isJson(responseBody);
    }

    /**
     * Briefly and very quickly checks whether response is JSON instead of
     * parsing probably long file and catching exceptions what takes much more CPU resources than this check
     *
     * @param responseBody String we want to check it is JSON
     * @return true if String is JSON
     */
    private static boolean isJson(final String responseBody) {
        return (responseBody.startsWith("{") && responseBody.endsWith("}")) ||
                (responseBody.startsWith("[") && responseBody.endsWith("]"));
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        final Response response = super.filter(requestSpec, responseSpec, ctx);
        String responseBody = response.getBody().asString();
        if (isMustBeTrimmed(responseBody)) {
            int trimLength = Math.min(responseBody.length(), LONG_RESPONSE_BODY_BYTES);
            responseBody = responseBody.substring(0, trimLength);
        }

        final String logText = String.format("%s %s %n%s %n%s ",
                requestSpec.getMethod(), requestSpec.getURI(),
                response.getStatusCode(), responseBody);
        log.info(logText);
        return response;
    }

}
