package com.github.instagram4j.instagram4j.requests.challenge;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.challenge.ChallengeStateResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChallengeStateGetRequest extends IGGetRequest<ChallengeStateResponse> {
    @NonNull
    private String path, guid, device_id;

    @Override
    public String path() {
        return path.substring(1);
    }

    @Override
    public String getQueryString(IGClient client) {
        return this.mapQueryString("guid", guid, "device_id", device_id);
    }

    @Override
    public Class<ChallengeStateResponse> getResponseType() {
        return ChallengeStateResponse.class;
    }

}
