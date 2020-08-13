package com.github.instagram4j.instagram4j.requests.multipleaccounts;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

public class MultipleAccountsGetAccountFamilyRequest extends IGGetRequest<IGResponse> {

    @Override
    public String path() {
        return "multiple_accounts/get_account_family/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
