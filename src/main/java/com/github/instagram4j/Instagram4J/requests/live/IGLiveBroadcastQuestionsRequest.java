package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGLiveBroadcastQuestionsRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _broadcast_id, _text;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private String text = _text;
        };
    }

    @Override
    public String path() {
        return "live/" + _broadcast_id + "/questions/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
