package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.live.LiveBroadcastLikeResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class LiveBroadcastLikeRequest extends IGPostRequest<LiveBroadcastLikeResponse> {
    @NonNull
    private String broadcast_id;
    private int count = 1;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private int user_like_count = count;
        };
    }

    @Override
    public String path() {
        return "live/" + broadcast_id + "/like/";
    }

    @Override
    public Class<LiveBroadcastLikeResponse> getResponseType() {
        return LiveBroadcastLikeResponse.class;
    }

}
