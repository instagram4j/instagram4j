package com.github.instagram4j.Instagram4J.exceptions;

import org.jetbrains.annotations.Nullable;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.responses.accounts.LoginResponse;

import lombok.Getter;

@Getter
public class LoginException extends Exception {
    @Nullable
    private final IGClient client;
    @Nullable
    private final LoginResponse loginResponse;

    public LoginException(IGClient client, LoginResponse body) {
        super(body.getMessage());
        this.client = client;
        this.loginResponse = body;
    }

    public LoginException(Throwable t) {
        super(t.getMessage(), t);
        this.client = null;
        this.loginResponse = null;
    }

}