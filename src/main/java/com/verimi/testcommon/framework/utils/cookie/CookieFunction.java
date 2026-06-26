package com.verimi.testcommon.framework.utils.cookie;

import org.openqa.selenium.Cookie;

@FunctionalInterface
public interface CookieFunction<TResult> {
    TResult run(Cookie cookie);
}

