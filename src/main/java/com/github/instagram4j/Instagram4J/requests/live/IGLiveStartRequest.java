package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.live.IGLiveStartResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class IGLiveStartRequest extends IGPostRequest<IGLiveStartResponse> {
    private String broadcastId;
    private boolean sendNotification;

    @Override
    protected IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            private boolean should_send_notifications = sendNotification;
        };
    }

    @Override
    public String path() {
        return "live/" + broadcastId + "/start/";
    }

    @Override
    public Class<IGLiveStartResponse> getResponseType() {
        return IGLiveStartResponse.class;
    }

}
