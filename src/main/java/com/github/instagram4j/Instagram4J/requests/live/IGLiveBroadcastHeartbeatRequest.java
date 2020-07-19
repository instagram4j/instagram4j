package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.live.IGLiveBroadcastHeartbeatResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGLiveBroadcastHeartbeatRequest extends IGPostRequest<IGLiveBroadcastHeartbeatResponse> {
    @NonNull
    private String broadcast_id;
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload();
    }

    @Override
    public String path() {
        return "live/" + broadcast_id + "/heartbeat_and_get_viewer_count/";
    }

    @Override
    public Class<IGLiveBroadcastHeartbeatResponse> getResponseType() {
        return IGLiveBroadcastHeartbeatResponse.class;
    }
    
}
