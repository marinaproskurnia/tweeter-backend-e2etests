package com.illichso.e2etests.steps.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBodyForExistingUser extends RequestBody {

    @JsonProperty("userId")
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
