package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.IGAccountsUserResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGAccountsActionRequest extends IGPostRequest<IGAccountsUserResponse> {
    @NonNull
    private IGAccountsAction action;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload();
    }

    @Override
    public String path() {
        return "accounts/" + action.name().toLowerCase() + "/";
    }

    @Override
    public Class<IGAccountsUserResponse> getResponseType() {
        return IGAccountsUserResponse.class;
    }

    public static enum IGAccountsAction {
        SET_PRIVATE, SET_PUBLIC, REMOVE_PROFILE_PICTURE;
    }
}
