package com.github.instagram4j.instagram4j.requests.accounts;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsLoginRequest.LoginPayload;
import com.github.instagram4j.instagram4j.responses.accounts.LoginResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountsTwoFactorLoginRequest extends IGPostRequest<LoginResponse> {
    @NonNull
    private String username, password, code, identifier;

    @Override
    public IGPayload getPayload(IGClient client) {
        return new LoginPayload(username, password) {
            @Getter
            private final String verification_code = code;
            @Getter
            private final String two_factor_identifier = identifier;
        };
    }

    @Override
    public String path() {
        return "accounts/two_factor_login/";
    }

    @Override
    public Class<LoginResponse> getResponseType() {
        return LoginResponse.class;
    }

}
