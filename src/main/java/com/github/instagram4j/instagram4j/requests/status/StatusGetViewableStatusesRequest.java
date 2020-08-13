package com.github.instagram4j.instagram4j.requests.status;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

public class StatusGetViewableStatusesRequest extends IGGetRequest<IGResponse> {

    @Override
    public String path() {
        return "status/get_viewable_statuses/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
}
