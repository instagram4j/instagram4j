package com.github.instagram4j.instagram4j.requests.accounts;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;

import java.util.Locale;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountsActionRequest extends IGPostRequest<AccountsUserResponse> {
    @NonNull
    private AccountsAction action;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload();
    }

    @Override
    public String path() {
        return "accounts/" + action.name().toLowerCase(new Locale("en", "US")) + "/";
    }

    @Override
    public Class<AccountsUserResponse> getResponseType() {
        return AccountsUserResponse.class;
    }

    public static enum AccountsAction {
        SET_PRIVATE, SET_PUBLIC, REMOVE_PROFILE_PICTURE;
    }
}
