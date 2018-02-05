package com.illichso.e2etests.steps.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBody {

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("text")
    private String text;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
