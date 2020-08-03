package com.github.instagram4j.instagram4j.exceptions;

import java.io.IOException;

import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Getter;

@Getter
public class IGResponseException extends IOException {
    private final IGResponse response;

    public IGResponseException(IGResponse response) {
        super(response.getMessage());
        this.response = response;
    }
}
