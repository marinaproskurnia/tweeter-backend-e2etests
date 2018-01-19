package com.illichso.e2etests;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class RequestBody {

    private String userName;
    private String text;

    public RequestBody() {
        System.out.println("####RequestBodyCreated");
    }

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
