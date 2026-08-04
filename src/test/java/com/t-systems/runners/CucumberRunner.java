package com.verimi.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features",
        glue = {
                "com/verimi/testcommon/config/hooks",
                "com/verimi/stepdefinitions"
        },
        plugin = {"pretty", "html:reports/cucumber.html", "json:reports/cucumber.json"})


public class CucumberRunner {
}

