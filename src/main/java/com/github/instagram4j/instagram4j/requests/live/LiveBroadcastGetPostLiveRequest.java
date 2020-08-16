package com.github.instagram4j.instagram4j.requests.live;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LiveBroadcastGetPostLiveRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private String _broadcast_string;

    @Override
    public String path() {
        return "live/" + _broadcast_string + "/get_post_live/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
