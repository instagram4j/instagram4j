package com.github.instagram4j.instagram4j.exceptions;

import com.github.instagram4j.instagram4j.exceptions.IGResponseException.IGFailedResponse;

public interface ExceptionallyHandler {

    public static final ExceptionallyHandler WRAPPED_TO_IGRESPONSE = new ExceptionallyHandler() {

        @Override
        public <T> T handle(Throwable throwable, Class<T> type) {
            return IGFailedResponse.of(throwable.getCause(), type);
        }

    };

    public <T> T handle(Throwable throwable, Class<T> type);
}
