package com.github.instagram4j.Instagram4J.exceptions;

import org.jetbrains.annotations.Nullable;

import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;

public class IGResponseException extends Exception {
    @Getter
    @Nullable
    private final Exception exception;
    @Getter
    @Nullable
    private final IGExceptionInfo info;

    public IGResponseException(IGExceptionInfo info, String message, Exception pass) {
        super(message);
        this.info = info;
        this.exception = pass;
    }

    @Getter
    @Setter
    @Builder
    public static class IGExceptionInfo {
        private Response response;
        private IGResponse igResponse;
    }
}
