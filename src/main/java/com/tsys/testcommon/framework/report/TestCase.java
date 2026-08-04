package com.tsys.testcommon.framework.report;

import java.time.Duration;

import org.apache.commons.lang.StringEscapeUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TestCase {
    @Setter
    @Getter
    public static String mobileDevice = "";
    private String title;
    private String result;
    private String log;
    private String[] acs;
    private String[] stories;
    private String[] bugs;
    private String details;
    private Throwable throwable;
    private String screenShotPath;
    private String browserstackReportUrl;
    private Duration execTime;
    private String description;

    public String getTitle() {
        return StringEscapeUtils.escapeHtml(title);
    }

    @SuppressWarnings("unused")
    public String getFormattedExecTime() {
        long seconds = execTime.getSeconds();
        // we don't want to display minutes when it is 0
        String text = seconds > 59 ? seconds / 60 + "m " : "";
        return text + seconds % 60 + "s";
    }
}
