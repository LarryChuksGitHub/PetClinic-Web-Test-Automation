package com.tsys.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features",
        glue = {
                "com/tsys/testcommon/config/hooks",
                "com/tsys/stepdefinitions"
        },
        plugin = {"pretty", "html:reports/cucumber.html", "json:reports/cucumber.json"})


public class CucumberRunner {
}

