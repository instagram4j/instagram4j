package com.github.instagram4j.Instagram4J.requests.qe;

import com.github.instagram4j.Instagram4J.IGConstants;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Getter;

public class IGQeSyncRequest extends IGPostRequest<IGResponse> {
    
    @Override
    protected IGPayload getPayload() {
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
