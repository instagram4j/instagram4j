package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.IGAccountsUserResponse;

public class IGAccountsCurrentUserRequest extends IGGetRequest<IGAccountsUserResponse> {

    @Override
    public String path() {
        return "accounts/current_user/";
    }

    @Override
    public Class<IGAccountsUserResponse> getResponseType() {
        return IGAccountsUserResponse.class;
    }

}
