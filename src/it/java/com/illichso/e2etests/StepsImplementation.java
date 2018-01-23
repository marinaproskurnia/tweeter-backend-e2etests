package com.illichso.e2etests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ValidatableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class StepsImplementation {

    @Value("${baseUri}")
    private String baseUri;

    @Autowired
    private RequestBody requestBody;

    private ValidatableResponse response;

    public void setUserName(String userName) {
        requestBody.setUserName(userName);
    }

    public void setPostText(String text) {
        requestBody.setText(text);
    }


    public void sendPostRequest() {
        response = RestAssured.given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().log().all()
                .post()
                .then().log().all();
    }


    public void checkResponseHttpStatus(int httpStatus) {
        response.assertThat().statusCode(httpStatus);
    }
}
