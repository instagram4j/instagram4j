package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGDirectThreadsRequest extends IGGetRequest<IGResponse> {
    @NonNull
    private String thread_id;
    private String cursor;
    private String seq_id;
    
    @Override
    public String path() {
        return "/direct_v2/threads/" + thread_id + "/";
    }
    
    @Override
    public String getQueryString() {
        return mapQueryString("cursor", cursor, "seq_id", seq_id);
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
