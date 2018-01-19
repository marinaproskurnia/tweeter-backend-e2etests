package com.illichso.e2etests;

import com.illichso.Application;
import com.illichso.e2etests.configuration.E2eTestsConfiguration;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features"},
        format = {
                "html:target/cucumber/plain-html-reports"
        })
public class ITRunner {

//    @PostConstruct
//    public void bootApplication() {
//        SpringApplication.run(E2eTestsConfiguration.class);
//    }
}

