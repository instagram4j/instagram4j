package com.github.instagram4j.instagram4j.requests.live;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LiveBroadcastGetPostLiveThumbnailsRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private String _broadcast_id;

    @Override
    public String path() {
        return "live/" + _broadcast_id + "/get_post_live_thumbnails/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
}
