package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

public class IGDirectGetPresenceRequest extends IGGetRequest<IGResponse> {

    @Override
    public String path() {
        return "direct_v2/get_presence/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
