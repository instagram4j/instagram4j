package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//TODO: test
public class IGAccountsSetBiographyRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String bio;

    @Override
    protected IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            private String raw_text = bio;
        };
    }

    @Override
    public String path() {
        return "accounts/set_biography/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
