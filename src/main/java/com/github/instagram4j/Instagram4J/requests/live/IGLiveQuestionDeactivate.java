package com.github.instagram4j.Instagram4J.requests.live;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGLiveQuestionDeactivate extends IGPostRequest<IGResponse> {
    @NonNull
    private String broadcast_id;
    @NonNull
    private String qid;

    @Override
    protected IGPayload getPayload() {
        return new IGPayload();
    }

    @Override
    public String path() {
        return String.format("live/%s/question/%s/deactivate/", broadcast_id, qid);
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
