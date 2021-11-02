package com.github.instagram4j.instagram4j.requests.live;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.live.LiveBroadcastThumbnailsResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LiveBroadcastGetPostLiveThumbnailsRequest extends IGGetRequest<LiveBroadcastThumbnailsResponse> {
    @NonNull
    private String _broadcast_id;

    @Override
    public String path() {
        return "live/" + _broadcast_id + "/get_post_live_thumbnails/";
    }

    @Override
    public Class<LiveBroadcastThumbnailsResponse> getResponseType() {
        return LiveBroadcastThumbnailsResponse.class;
    }

}
