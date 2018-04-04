package com.illichso.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@JsonInclude
public class NewUserPost implements Serializable {
    private String userName;
    private String text;
}
