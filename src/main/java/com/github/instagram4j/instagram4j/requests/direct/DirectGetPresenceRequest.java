package com.github.instagram4j.instagram4j.requests.direct;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.direct.DirectGetPresenceResponse;

public class DirectGetPresenceRequest extends IGGetRequest<DirectGetPresenceResponse> {

    @Override
    public String path() {
        return "direct_v2/get_presence/";
    }

    @Override
    public Class<DirectGetPresenceResponse> getResponseType() {
        return DirectGetPresenceResponse.class;
    }

}
