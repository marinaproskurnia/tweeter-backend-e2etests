package com.illichso.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonInclude
public class UserRepost implements Serializable {
    private long userId;
    private long postId;
}
