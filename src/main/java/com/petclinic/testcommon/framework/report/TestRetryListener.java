package com.petclinic.testcommon.framework.report;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import com.petclinic.testcommon.framework.utils.retry.RetryAnalyzer;

@SuppressWarnings("unused")
public class TestRetryListener implements IAnnotationTransformer {

    public void transform(final ITestAnnotation annotation, final Class testClass, final Constructor testConstructor,
                          final Method testMethod) {
        IRetryAnalyzer retryAnalyzer = annotation.getRetryAnalyzer();
        if (retryAnalyzer == null) {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }
}
