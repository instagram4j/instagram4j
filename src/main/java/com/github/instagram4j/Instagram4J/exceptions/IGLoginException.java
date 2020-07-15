package com.github.instagram4j.Instagram4J.exceptions;

import org.jetbrains.annotations.Nullable;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;

import lombok.Getter;

@Getter
public class IGLoginException extends Exception {
    @Nullable
    private final IGClient client;
    @Nullable
    private final IGLoginResponse loginResponse;

    public IGLoginException(IGClient client, IGLoginResponse body) {
        super(body.getMessage());
        this.client = client;
        this.loginResponse = body;
    }

    public IGLoginException(Throwable t) {
        super(t.getMessage(), t);
        this.client = null;
        this.loginResponse = null;
    }

}