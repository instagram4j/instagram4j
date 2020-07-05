package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGLiveBroadcastInfoRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private String _broadcast_id;
    
    @Override
    public String path() {
        return "live/" + _broadcast_id + "/info/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
