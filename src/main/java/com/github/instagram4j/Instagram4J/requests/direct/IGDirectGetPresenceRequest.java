package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.direct.IGDirectGetPresenceResponse;

public class IGDirectGetPresenceRequest extends IGGetRequest<IGDirectGetPresenceResponse> {

    @Override
    public String path() {
        return "direct_v2/get_presence/";
    }

    @Override
    public Class<IGDirectGetPresenceResponse> getResponseType() {
        return IGDirectGetPresenceResponse.class;
    }

}
