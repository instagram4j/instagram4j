package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.live.IGLiveCreateResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class IGLiveCreateRequest extends IGPostRequest<IGLiveCreateResponse> {
    private int width = 720, height = 1280;
    private String broadcastMessage = "test", broadcastType = "RTMP";
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGLiveCreatePayload(width, height, broadcastMessage, broadcastType);
    }

    @Override
    public String path() {
        return "live/create/";
    }

    @Override
    public Class<IGLiveCreateResponse> getResponseType() {
        return IGLiveCreateResponse.class;
    }
    
    @Data
    @AllArgsConstructor
    public static class IGLiveCreatePayload extends IGPayload {
        private int preview_width;
        private int preview_height;
        private String broadcast_message;
        private String broadcast_type;
    }

}
