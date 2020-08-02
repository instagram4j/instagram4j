package com.github.instagram4j.Instagram4J.exceptions;

import java.io.IOException;

import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Getter;

@Getter
public class IGResponseException extends IOException {
    private final IGResponse response;

    public IGResponseException(IGResponse response) {
        super(response.getMessage());
        this.response = response;
    }
}
