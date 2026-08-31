package com.petclinic.testcommon.framework.report;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.petclinic.testcommon.framework.utils.retry.RetryAnalyzer;

/**
 * Adds retry analyzer to rerun failed test 2 times until it is completely marked as failed
 */
@SuppressWarnings("unused")
public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}