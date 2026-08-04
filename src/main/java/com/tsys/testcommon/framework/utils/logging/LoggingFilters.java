package com.tsys.testcommon.framework.utils.logging;

import java.util.List;

import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggingFilters {

    private static final List<Filter> FILTERS = List.of(
            new RequestLoggingFilter(),
            new CustomResponseLoggingFilter()
    );

    public static List<Filter> getFilters() {
        return FILTERS;
    }
}
