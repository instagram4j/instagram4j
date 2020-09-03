package com.github.instagram4j.instagram4j.requests.challenge;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGBaseModel;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.challenge.ChallengeStateResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeSendPhonenumberRequest extends IGPostRequest<ChallengeStateResponse> {
    @NonNull
    private final String path;
    @NonNull
    private final String _phone_number;
    
    @Override
    protected IGBaseModel getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private String phone_number = _phone_number; 
        };
    }

    @Override
    public String path() {
        return path.substring(1);
    }

    @Override
    public Class<ChallengeStateResponse> getResponseType() {
        return ChallengeStateResponse.class;
    }

}
