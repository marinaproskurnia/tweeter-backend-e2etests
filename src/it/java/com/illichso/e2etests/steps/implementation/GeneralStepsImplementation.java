package com.illichso.e2etests.steps.implementation;

import com.illichso.e2etests.steps.components.RequestBody;
import com.illichso.e2etests.steps.components.RequestBodyForExistingUser;
import com.illichso.h2DataBase.DeleteOperations;
import com.illichso.h2DataBase.InsertOperations;
import com.illichso.h2DataBase.SelectOperations;
import com.jayway.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

@Getter
public class GeneralStepsImplementation {

    @Setter
    private ValidatableResponse response;
    private long postId;

    @Value("${e2etest.baseURI}")
    private String baseUri;

    @Value("${e2etest.port}")
    private int port;

    @Value("${e2etest.basePathForPost}")
    private String basePathForPost;

    @Value("${e2etest.basePathForDelete}")
    private String basePathForDelete;

    @Resource(name = "requestBodyForNewUser")
    private RequestBody requestBodyForNewUser;

    @Resource(name = "requestBodyForExistingUser")
    private RequestBodyForExistingUser requestBodyForExistingUser;

    @Autowired
    private InsertOperations insertOperations;

    @Autowired
    private DeleteOperations deleteOperations;

    @Autowired
    private SelectOperations selectOperations;

    public void setNewUserName(String userName) {
        requestBodyForNewUser.setUserName(userName);
    }

    public void setExistingUserName(String userName) {
        requestBodyForExistingUser.setUserName(userName);
    }

    public void setPostTextForNewUser(String text) {
        requestBodyForNewUser.setText(text);
    }

    public void setPostTextForExistingUser(String text) {
        requestBodyForExistingUser.setText(text);
    }

    public void setPostIdFromPostsDbTable() {
        String name = requestBodyForNewUser.getUserName();
        String text = requestBodyForNewUser.getText();
        postId = selectOperations.selectPostIdFromPostsDbTable(name, text);
    }

    public void setUserIdFromUsersDbTable() {
        String name = requestBodyForNewUser.getUserName();
        long userId = selectOperations.selectUserIdFromUsersDbTable(name);
        requestBodyForExistingUser.setUserId(userId);
    }

    public final void tearDown() {
        deleteOperations.deleteFromUsersTable();
        deleteOperations.deleteFromPostsTable();
    }
}
