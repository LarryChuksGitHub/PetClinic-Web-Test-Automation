package com.tsys.testcommon.framework.utils.logging;

public final class LogUtils {

    private LogUtils() {
    }

    public static String createMessageWithBorder(String message, String pattern) {
        StringBuilder border = new StringBuilder();
        border.append('\n');
        for (int i = 0; i < message.length(); i++) {
            border.append(pattern);
        }
        return border.append('\n').append(message).toString();
    }
}
