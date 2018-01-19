package com.illichso.e2etests;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Value;

public class StepsDefinitions {

    private StepsImplementation steps;

    @Before
    public void initialise() {
        steps = new StepsImplementation();
    }

    @Given("^the new user provides his ([^\"]*)$")
    public void setUserName(String userName) {
        steps.setUserName(userName);
    }

    @And("^the new user posts some ([^\"]*)$")
    public void setPostText(String text) {
        steps.setPostText(text);
    }

    @When("^the POST request is sent$")
    public void sendRequest() {
        steps.sendPostRequest();
    }
    @Then("^the status of the response should be (\\d+)$")
    public void checkResponseHttpStatus(int httpStatus) {
        steps.checkResponseHttpStatus(httpStatus);
    }
}
