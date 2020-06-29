package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGDirectThreadsLeaveRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _thread_id;
    
    @Override
    protected IGPayload getPayload() {
        return new IGPayload();
    }

    @Override
    public String path() {
        return "direct_v2/threads/" + this._thread_id + "/leave/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
}
