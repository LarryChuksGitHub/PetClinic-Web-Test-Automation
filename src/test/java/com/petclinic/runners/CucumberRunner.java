package com.petclinic.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features",
        glue = {
                "com/petclinic/testcommon/config/hooks",
                "com/petclinic/stepdefinitions"
        },
        plugin = {"pretty", "html:reports/cucumber.html", "json:reports/cucumber.json"})


public class CucumberRunner {
}

