package com.github.instagram4j.instagram4j.requests.direct;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.direct.DirectThreadsResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class DirectThreadsRequest extends IGGetRequest<DirectThreadsResponse> {
    @NonNull
    private String thread_id;
    private String cursor;

    @Override
    public String path() {
        return "direct_v2/threads/" + thread_id + "/";
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("cursor", cursor);
    }

    @Override
    public Class<DirectThreadsResponse> getResponseType() {
        return DirectThreadsResponse.class;
    }

}
