package com.illichso.e2etests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features"},
        format = {"html:target/cucumber/plain-html-reports"})
public class ITRunner {
}
