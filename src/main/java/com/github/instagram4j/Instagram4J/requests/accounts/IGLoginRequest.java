package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGLoginRequest extends IGPostRequest<IGLoginResponse> {
    @NonNull
    private String username;
    @NonNull
    private String password;

    @Override
    public String path() {
        return "/accounts/login/";
    }

    @Override
    public IGPayload getPayload() {
        LoginPayload payload = LoginPayload.builder().username(username).password(password).build();

        return payload;
    }

    @Override
    public Class<IGLoginResponse> getResponseType() {
        return IGLoginResponse.class;
    }

    @Data
    @Builder
    public static class LoginPayload extends IGPayload {
        private String username;
        private String password;
        @Builder.Default
        private int login_attempt_account = 0;
    }

}
