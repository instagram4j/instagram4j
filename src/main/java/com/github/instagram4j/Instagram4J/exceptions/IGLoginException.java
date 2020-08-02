package com.github.instagram4j.Instagram4J.exceptions;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.responses.accounts.LoginResponse;

import lombok.Getter;

@Getter
public class IGLoginException extends IGResponseException {
    private final IGClient client;
    private final LoginResponse loginResponse;

    public IGLoginException(IGClient client, LoginResponse body) {
        super(body);
        this.client = client;
        this.loginResponse = body;
    }
    
}