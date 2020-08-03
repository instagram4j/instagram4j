package com.github.instagram4j.instagram4j.requests;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import okhttp3.Request;

public abstract class IGGetRequest<T extends IGResponse> extends IGRequest<T> {

    @Override
    public Request formRequest(IGClient client) {
        Request.Builder req = new Request.Builder()
                .url(this.formUrl(client));
        this.applyHeaders(client, req);

        return req.build();
    }
}
