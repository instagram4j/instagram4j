package com.github.instagram4j.Instagram4J.requests.challenge;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.challenge.IGChallengeStateResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGChallengeStateGetRequest extends IGGetRequest<IGChallengeStateResponse> {
    @NonNull
    private String path, guid, device_id;

    @Override
    public String path() {
        return path.substring(1);
    }

    @Override
    public String getQueryString() {
        return this.mapQueryString("guid", guid, "device_id", device_id);
    }

    @Override
    public Class<IGChallengeStateResponse> getResponseType() {
        return IGChallengeStateResponse.class;
    }

}
