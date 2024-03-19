package com.example.kledo.productkledo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BearerRequest {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("remember_me")
    private int rememberMe;
}

