package com.github.instagram4j.instagram4j.requests.status;

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
public class StatusSetStatusRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private StatusSetStatusRequest.StatusSetStatusPayload payload;

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
    public static class StatusSetStatusPayload extends IGPayload {
        @NonNull
        @JsonProperty("text")
        private String text;
        @NonNull
        @JsonProperty("emoji")
        private String emoji;
        @JsonProperty("expires_at")
        private long expires_at;
        @JsonProperty("should_notify")
        private boolean should_notify;
        @NonNull
        @JsonProperty("status_type")
        private String status_type;

        public StatusSetStatusPayload(String text, String emoji, long expires_at) {
            this.text = text;
            this.emoji = emoji;
            this.expires_at = expires_at;
            this.should_notify = false;
            this.status_type = "manual";
        }

        public StatusSetStatusPayload(String text, String emoji, long expires_at, boolean should_notify, String status_type) {
            this.text = text;
            this.emoji = emoji;
            this.expires_at = expires_at;
            this.should_notify = should_notify;
            this.status_type = status_type;
        }
    }

}
