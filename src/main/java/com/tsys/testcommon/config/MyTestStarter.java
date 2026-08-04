package com.tsys.testcommon.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

public class MyTestStarter {

    public static void main(String[] args) throws IOException {
        TestNG testng = new TestNG();
        List<XmlSuite> suite;

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("testng_suite.xml");

        suite = (List<XmlSuite>) (new Parser(inputStream).parse());
        testng.setXmlSuites(suite);
        testng.run();
        if (testng.hasFailure() || testng.hasSkip()) {
            System.exit(1);
        } else {
            System.exit(0);
        }
    }
}
