package com.tsys.testcommon.framework.utils.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.tsys.testcommon.framework.utils.logging.LoggingFilters;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.specification.RequestSpecification;

public abstract class BaseRequest {

    protected CookieFilter cookieFilter;
    private RequestSpecification spec;

    static {
        // because RestAssured's config is static we don't want to replace it every time
        configureObjectMapper();
    }

    protected BaseRequest(CookieFilter cookieFilter) {
        this.cookieFilter = cookieFilter;
    }

    protected BaseRequest(RequestSpecification spec) {
        this.spec = spec;
    }

    protected BaseRequest(CookieFilter cookieFilter, RequestSpecification spec) {
        this.cookieFilter = cookieFilter;
        this.spec = spec;
    }

    private static void configureObjectMapper() {
        RestAssured.config = RestAssured.config.objectMapperConfig(ObjectMapperConfig.objectMapperConfig()
                .jackson2ObjectMapperFactory((cls, charset) -> {
                    JavaTimeModule javaTimeModule = new JavaTimeModule();
                    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
                    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
                    return new ObjectMapper()
                            .findAndRegisterModules()
                            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                            .registerModule(javaTimeModule);
                }));
    }

    /**
     * Returns preconfigured instance of RestAssured with all cookies and logs configured
     */
    protected RequestSpecification given() {
        RequestSpecification requestSpecification = RestAssured.given();
        if (spec != null) {
            requestSpecification = requestSpecification.spec(spec);
        }
        if (cookieFilter != null) {
            requestSpecification = requestSpecification.filter(cookieFilter);
        }
        return requestSpecification
                .filters(LoggingFilters.getFilters());
    }
}
