package com.petclinic.testcommon.framework.utils.sleep;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class SleepUtil {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.warn("Thread Interrupted!", e);
            Thread.currentThread().interrupt();
        }
    }
}
