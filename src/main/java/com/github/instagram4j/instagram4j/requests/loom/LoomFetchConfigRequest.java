package com.github.instagram4j.instagram4j.requests.loom;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

public class LoomFetchConfigRequest extends IGGetRequest<IGResponse> {

    @Override
    public String path() {
        return "loom/fetch_config/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
