package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

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
