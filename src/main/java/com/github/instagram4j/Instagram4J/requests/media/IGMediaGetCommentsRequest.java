package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaGetCommentsResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGMediaGetCommentsRequest extends IGGetRequest<IGMediaGetCommentsResponse> {
    @NonNull
    private String _id;
    private String _max_id;

    @Override
    public String path() {
        return "media/" + _id + "/comments/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", _max_id);
    }

    @Override
    public Class<IGMediaGetCommentsResponse> getResponseType() {
        return IGMediaGetCommentsResponse.class;
    }
}
