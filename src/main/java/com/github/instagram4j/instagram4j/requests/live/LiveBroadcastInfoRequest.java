package com.github.instagram4j.instagram4j.requests.live;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LiveBroadcastInfoRequest extends IGGetRequest<IGResponse> {
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
