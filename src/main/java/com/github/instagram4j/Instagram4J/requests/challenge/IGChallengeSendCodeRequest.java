package com.github.instagram4j.Instagram4J.requests.challenge;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGChallengeSendCodeRequest extends IGPostRequest<IGLoginResponse> {
    @NonNull
    private String path;
    @NonNull
    private String code;

    @Override
    public IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            private final String security_code = code;
        };
    }

    @Override
    public String path() {
        return path.substring(1);
    }

    @Override
    public Class<IGLoginResponse> getResponseType() {
        return IGLoginResponse.class;
    }

}
