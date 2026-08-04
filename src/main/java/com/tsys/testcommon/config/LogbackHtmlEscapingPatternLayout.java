package com.tsys.testcommon.config;

import org.apache.commons.lang.StringEscapeUtils;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LogbackHtmlEscapingPatternLayout extends PatternLayout {

    @Override
    public String doLayout(ILoggingEvent event) {
        return StringEscapeUtils.escapeHtml(super.doLayout(event));
    }
}
