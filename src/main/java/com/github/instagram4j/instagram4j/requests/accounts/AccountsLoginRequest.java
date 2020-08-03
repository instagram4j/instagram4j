package com.github.instagram4j.instagram4j.requests.accounts;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.accounts.LoginResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountsLoginRequest extends IGPostRequest<LoginResponse> {
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
    public Class<LoginResponse> getResponseType() {
        return LoginResponse.class;
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
