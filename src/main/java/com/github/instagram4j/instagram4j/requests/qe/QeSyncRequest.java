package com.github.instagram4j.instagram4j.requests.qe;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.IGConstants;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Getter;

public class QeSyncRequest extends IGPostRequest<IGResponse> {
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private String experiments = IGConstants.DEVICE_EXPERIMENTS;
        };
    }

    @Override
    public String path() {
        return "qe/sync/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
