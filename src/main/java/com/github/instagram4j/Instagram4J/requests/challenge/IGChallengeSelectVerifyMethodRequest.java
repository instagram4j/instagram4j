package com.github.instagram4j.Instagram4J.requests.challenge;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.challenge.IGChallengeStateResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGChallengeSelectVerifyMethodRequest extends IGPostRequest<IGChallengeStateResponse> {
    @NonNull
    private final String path;
    @NonNull
    private final String _choice;
    private final boolean resend;

    @Override
    public IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            private final String choice = _choice;
        };
    }

    @Override
    public String path() {
        return !resend ? path : path.replace("/challenge/", "/challenge/replay/");
    }

    @Override
    public Class<IGChallengeStateResponse> getResponseType() {
        return IGChallengeStateResponse.class;
    }

}
