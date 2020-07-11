package com.github.instagram4j.Instagram4J.requests.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.IGAccountsUserResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
public class IGAccountsEditProfileRequest extends IGPostRequest<IGAccountsUserResponse> {
    @NonNull
    private IGAccountsEditProfilePayload payload;

    @Override
    protected IGPayload getPayload() {
        return payload;
    }

    @Override
    public String path() {
        return "accounts/edit_profile/";
    }

    @Override
    public Class<IGAccountsUserResponse> getResponseType() {
        return IGAccountsUserResponse.class;
    }

    @Data
    @Accessors(fluent = true)
    public static class IGAccountsEditProfilePayload extends IGPayload {
        @JsonProperty("first_name")
        private String first_name;
        @JsonProperty("biography")
        private String biography;
        @NonNull
        @JsonProperty("username")
        private final String username;
        @JsonProperty("phone_number")
        private String phone_number;
        @NonNull
        @JsonProperty("email")
        private final String email;
        @JsonProperty("external_url")
        private String external_url;
    }

}
