package com.illichso.e2etests.steps.implementation;

import com.illichso.e2etests.steps.components.RequestBody;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;

public class HttpRequestStepsImplementation {

    private static final String POST_ID_PATH_PARAM = "postId";

    @Autowired
    private GeneralStepsImplementation generalSteps;

    public void sendPostRequestForNewUser() {
        sendPostRequest(generalSteps.getRequestBodyForNewUser());
    }

    public void sendPostRequestForExistingUser() {
        sendPostRequest(generalSteps.getRequestBodyForExistingUser());
    }

    public void sendDeleteRequest() {
        ValidatableResponse response = createCommonRequestSpecification(generalSteps.getBasePathForDelete())
                .pathParam(POST_ID_PATH_PARAM, generalSteps.getPostId())
                .when().log().all()
                .delete()
                .then().log().all();
        generalSteps.setResponse(response);
    }

    private RequestSpecification createCommonRequestSpecification(String basePath) {
        return RestAssured.given()
                .baseUri(generalSteps.getBaseUri())
                .port(generalSteps.getPort())
                .basePath(basePath);
    }

    private void sendPostRequest(RequestBody requestBody) {
        RequestSpecification requestSpecification = createCommonRequestSpecification(generalSteps.getBasePathForPost());
        ValidatableResponse response = requestSpecification
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().log().all()
                .post()
                .then().log().all();
        generalSteps.setResponse(response);
    }
}
