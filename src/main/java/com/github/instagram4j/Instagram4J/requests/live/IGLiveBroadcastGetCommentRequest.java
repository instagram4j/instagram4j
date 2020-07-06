package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.live.IGLiveBroadcastGetCommentResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGLiveBroadcastGetCommentRequest extends IGGetRequest<IGLiveBroadcastGetCommentResponse> {
    @NonNull
    private String broadcast_id;
    private long last_ts;

    @Override
    public String path() {
        return "live/" + broadcast_id + "/get_comment/";
    }
    
    @Override
    public String getQueryString() {
        return mapQueryString("last_comment_ts", String.valueOf(last_ts));
    }

    @Override
    public Class<IGLiveBroadcastGetCommentResponse> getResponseType() {
        return IGLiveBroadcastGetCommentResponse.class;
    }

}
