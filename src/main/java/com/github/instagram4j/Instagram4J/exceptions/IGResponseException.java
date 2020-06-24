package com.github.instagram4j.Instagram4J.exceptions;

import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import okhttp3.Response;

@Getter
public class IGResponseException extends Exception {
    @Nullable
    private final Response response;

    public IGResponseException(Response response, String message, Throwable pass) {
        super(message + " " + pass.getMessage(), pass);
        this.response = response;
    }
}
