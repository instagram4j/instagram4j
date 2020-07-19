package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;

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
        return "accounts/login/";
    }

    @Override
    public IGPayload getPayload(IGClient client) {
        return new LoginPayload(username, password);
    }

    @Override
    public Class<IGLoginResponse> getResponseType() {
        return IGLoginResponse.class;
    }

    @Data
    public static class LoginPayload extends IGPayload {
        @NonNull
        private String username;
        @NonNull
        private String enc_password;
        private int login_attempt_account = 0;
    }

}
