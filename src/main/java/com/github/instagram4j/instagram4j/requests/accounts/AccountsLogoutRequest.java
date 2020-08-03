package com.github.instagram4j.instagram4j.requests.accounts;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

public class AccountsLogoutRequest extends IGPostRequest<IGResponse> {

    @Override
    public String path() {
        return "accounts/logout/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload();
    }

}
