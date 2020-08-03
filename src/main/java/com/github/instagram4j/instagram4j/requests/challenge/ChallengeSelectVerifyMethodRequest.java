package com.github.instagram4j.instagram4j.requests.challenge;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.challenge.ChallengeStateResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeSelectVerifyMethodRequest extends IGPostRequest<ChallengeStateResponse> {
    @NonNull
    private final String path;
    @NonNull
    private final String _choice;
    private final boolean resend;

    @Override
    public IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private final String choice = _choice;
        };
    }

    @Override
    public String path() {
        return !resend ? path.substring(1) : path.replace("/challenge/", "challenge/replay/");
    }

    @Override
    public Class<ChallengeStateResponse> getResponseType() {
        return ChallengeStateResponse.class;
    }

}
