package com.github.instagram4j.Instagram4J.exceptions;

import org.jetbrains.annotations.Nullable;

import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;

import lombok.Getter;

@Getter
public class IGLoginException extends Exception {
    @Nullable
    private IGLoginResponse response;

    public IGLoginException(IGLoginResponse body) {
        super(body.getMessage());
        this.response = body;
    }
    
    public IGLoginException(Throwable t) {
        super(t.getMessage(), t);
    }

}