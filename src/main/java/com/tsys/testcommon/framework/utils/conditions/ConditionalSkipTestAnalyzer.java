package com.tsys.testcommon.framework.utils.conditions;

import static com.tsys.testcommon.config.Config.isAndroid;

import java.lang.reflect.Method;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

@SuppressWarnings("unused")
public class ConditionalSkipTestAnalyzer implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();

        if (method != null) {
            RunCondition runCondition = method.getAnnotation(RunCondition.class);

            if (runCondition != null) {
                boolean isAndroidRun = runCondition.androidRun();
                if (isAndroid() && !isAndroidRun) {
                    throw new SkipException("Skipping the test for Android Run!");
                }
            }
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }
}
