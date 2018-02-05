package com.illichso.e2etests.steps.definitions;

import com.illichso.e2etests.steps.implementation.AssertStepsImplementation;
import com.illichso.e2etests.steps.implementation.GeneralStepsImplementation;
import com.illichso.e2etests.steps.implementation.HttpRequestStepsImplementation;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:cucumber.xml")
@SpringBootTest
public class StepsDefinitions {

    @Autowired
    private GeneralStepsImplementation generalSteps;
    @Autowired
    private HttpRequestStepsImplementation httpRequestSteps;
    @Autowired
    private AssertStepsImplementation assertSteps;

    @After
    public final void tearDown() {
        generalSteps.tearDown();
    }

    @Given("^the new user provides his ([^\"]*)$")
    public void setNewUserName(String userName) {
        generalSteps.setNewUserName(userName);
    }

    @Given("^the post id has been retrieved from DB$")
    public void setPostIdFromPostsDbTable() {
        generalSteps.setPostIdFromPostsDbTable();
    }

    @Given("^the user id has been retrieved from DB$")
    public void setUserIdFromUsersDbTable() {
        generalSteps.setUserIdFromUsersDbTable();
    }

    @And("^the user provides his ([^\"]*)$")
    public void setExistingUserName(String userName) {
        generalSteps.setExistingUserName(userName);
    }

    @And("^the new user posts some ([^\"]*)$")
    public void setPostTextForNewUser(String text) {
        generalSteps.setPostTextForNewUser(text);
    }

    @And("^the user posts some ([^\"]*)$")
    public void setPostTextForExistingUser(String text) {
        generalSteps.setPostTextForExistingUser(text);
    }

    @When("^the POST request for the new user is sent$")
    public void sendPostRequestForNewUser() {
        httpRequestSteps.sendPostRequestForNewUser();
    }

    @When("^the POST request for the existing user is sent$")
    public void sendPostRequestForExistingUser() {
        httpRequestSteps.sendPostRequestForExistingUser();
    }

    @When("^the DELETE request is sent$")
    public void sendDeleteRequest() {
        httpRequestSteps.sendDeleteRequest();
    }

    @Then("^the status of the response should be (\\d+)$")
    public void checkResponseHttpStatus(int httpStatus) {
        assertSteps.assertThatResponseHasExpectedHttpStatus(httpStatus);
    }

    @And("^the post record has been added to DB$")
    public void checkIsRecordAddedToDbTable() {
        assertSteps.assertThatPostRecordAddedToDbTable();
    }
}
