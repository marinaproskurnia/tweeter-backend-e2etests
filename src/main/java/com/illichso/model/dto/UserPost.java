package com.illichso.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonInclude
public class UserPost implements Serializable {
    private long userId;
    private String userName;
    private String text;
}
