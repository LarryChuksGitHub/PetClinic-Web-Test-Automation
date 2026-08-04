package com.tsys.testcommon.framework.report;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Reference {
    String[] story() default {};

    /**
     * @return used to track AFO and test case ids for gematik
     */
    String[] ac() default {};

    String[] bug() default {};

    String[] api() default {};

    String[] testId() default {};
}
