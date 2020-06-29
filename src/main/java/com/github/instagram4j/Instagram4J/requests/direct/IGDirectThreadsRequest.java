package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.direct.IGDirectThreadsResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGDirectThreadsRequest extends IGGetRequest<IGDirectThreadsResponse> {
    @NonNull
    private String thread_id;
    private String cursor;
    private String seq_id;
    
    @Override
    public String path() {
        return "direct_v2/threads/" + thread_id + "/";
    }
    
    @Override
    public String getQueryString() {
        return mapQueryString("cursor", cursor, "seq_id", seq_id);
    }

    @Override
    public Class<IGDirectThreadsResponse> getResponseType() {
        return IGDirectThreadsResponse.class;
    }

}
