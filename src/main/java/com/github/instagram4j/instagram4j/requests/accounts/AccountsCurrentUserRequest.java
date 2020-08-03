package com.github.instagram4j.instagram4j.requests.accounts;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;

public class AccountsCurrentUserRequest extends IGGetRequest<AccountsUserResponse> {

    @Override
    public String path() {
        return "accounts/current_user/";
    }

    @Override
    public Class<AccountsUserResponse> getResponseType() {
        return AccountsUserResponse.class;
    }

}
