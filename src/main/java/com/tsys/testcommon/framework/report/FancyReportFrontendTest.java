package com.tsys.testcommon.framework.report;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

public class FancyReportFrontendTest {

    @Test
    public void testGenerateReport() {
        addTestCases();
        List<XmlSuite> xmlSuites = mock(ArrayList.class);
        List<ISuite> suites = mock(ArrayList.class);
        TestExecutionRegistry.setTestEnvUrl("test-env-url");
        TestExecutionRegistry.setTestSide(TestSide.FRONTEND);
        new ShinyHtmlReport().generateReport(xmlSuites, suites, "");
    }

    private void addTestCases() {
        addCase(ITestResult.SUCCESS);
        addCase(ITestResult.FAILURE);
        addCase(ITestResult.SKIP);
    }

    private void addCase(int result) {
        Reference reference = mock(Reference.class);
        when(reference.api()).thenReturn(new String[0]);
        when(reference.story()).thenReturn(new String[]{});
        when(reference.ac()).thenReturn(new String[]{});
        when(reference.bug()).thenReturn(new String[]{});
        ITestResult testresult = mock(ITestResult.class);
        when(testresult.getStatus()).thenReturn(result);
        when(testresult.getName()).thenReturn("dummyMethod has a long naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaame");
        when(testresult.getParameters()).thenReturn(new String[]{"ein", "two", "3"});
        if(result == ITestResult.FAILURE) {
            when(testresult.getAttribute(TestExecutionRegistry.SCREENSHOT_ATTRIBUTE)).thenReturn("https://cdn-images-1.medium.com/max/1000/1*fRSUOSUDXqwBWKBHvg2jcA.png");
            when(testresult.getThrowable()).thenReturn(new FormatFlagsConversionMismatchException("string",'c'));
        }
        TestExecutionRegistry.addTestCase(reference, testresult);
    }
}
