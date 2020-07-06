package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.live.IGLiveBroadcastLikeResponse.IGLiveBroadcastGetLikeCountResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGLiveBroadcastGetLikeCountRequest extends IGGetRequest<IGLiveBroadcastGetLikeCountResponse> {
    @NonNull
    private String broadcast_id;
    private long like_ts;
    
    @Override
    public String path() {
        return "live/" + broadcast_id + "/get_like_count/";
    }
    
    @Override
    public String getQueryString() {
        return mapQueryString("like_ts", String.valueOf(like_ts));
    }

    @Override
    public Class<IGLiveBroadcastGetLikeCountResponse> getResponseType() {
        return IGLiveBroadcastGetLikeCountResponse.class;
    }

}
