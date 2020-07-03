package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

public class IGMediaConfigureToIgtvRequest extends IGPostRequest<IGResponse> {

    @Override
    protected IGPayload getPayload() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String path() {
        return "media/configure_to_igtv/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
