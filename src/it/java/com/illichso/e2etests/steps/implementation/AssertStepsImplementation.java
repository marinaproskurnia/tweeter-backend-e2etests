package com.illichso.e2etests.steps.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AssertStepsImplementation {

    private static final long EXPECTED_POSTS_NUMBER = 1;

    @Autowired
    private GeneralStepsImplementation generalSteps;

    public void assertThatResponseHasExpectedHttpStatus(int httpStatus) {
        generalSteps.getResponse().assertThat().statusCode(httpStatus);
    }

    public void assertThatPostRecordAddedToDbTable() {
        String name = generalSteps.getRequestBodyForNewUser().getUserName();
        String text = generalSteps.getRequestBodyForNewUser().getText();
        long numberOfPostsMatchingCriteria = generalSteps.getSelectOperations().countRecordsFromPostsTable(name, text);
        assertThat(numberOfPostsMatchingCriteria, equalTo(EXPECTED_POSTS_NUMBER));
    }
}
