package com.github.instagram4j.Instagram4J.requests;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.IGConstants;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import okhttp3.Request;

public abstract class IGGetRequest<T extends IGResponse> extends IGRequest<T> {

    @Override
    public Request formRequest(IGClient client) {
        Request.Builder req = new Request.Builder()
                .url(IGConstants.BASE_API_URL + this.apiPath() + this.path() + this.getQueryString());
        this.applyHeaders(client, req);

        return req.build();
    }
}
