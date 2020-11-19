package com.github.instagram4j.instagram4j.requests.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class AccountsEditStatusRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private AccountsEditStatusPayload payload;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return payload;
    }

    @Override
    public String path() {
        return "status/set_status/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    @Data
    @Accessors(fluent = true)
    public static class AccountsEditStatusPayload extends IGPayload {
        @NonNull
        @JsonProperty("text")
        private String text;
        @NonNull
        @JsonProperty("emoji")
        private String emoji;
        @NonNull
        @JsonProperty("expires_at")
        private long expires_at;
        @NonNull
        @JsonProperty("should_notify")
        private boolean should_notify;
        @NonNull
        @JsonProperty("status_type")
        private String status_type;
    }

}
