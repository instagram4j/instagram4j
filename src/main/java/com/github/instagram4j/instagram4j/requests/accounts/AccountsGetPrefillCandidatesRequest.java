package com.github.instagram4j.instagram4j.requests.accounts;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.IGConstants;
import com.github.instagram4j.instagram4j.models.IGBaseModel;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

public class AccountsGetPrefillCandidatesRequest extends IGPostRequest<IGResponse> {

    @Override
    public String baseApiUrl() {
        return IGConstants.B_BASE_API_URL;
    }

    @Override
    protected IGBaseModel getPayload(IGClient client) {
        return new PrePayload(client.getDeviceId(), client.getPhoneId(), client.getGuid());
    }

    @Override
    public String path() {
        return "accounts/get_prefill_candidates/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    @Data
    private class PrePayload extends IGBaseModel {
        private final String android_device_id;
        private final String phone_id;
        private final String device_id;
        private final String usages = "[\"account_recovery_omnibox\"]";
    }

}
