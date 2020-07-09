package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaInfoResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGMediaInfoRequest extends IGGetRequest<IGMediaInfoResponse> {
    @NonNull
    private String id;

    @Override
    public String path() {
        return "media/" + id + "/info/";
    }

    @Override
    public Class<IGMediaInfoResponse> getResponseType() {
        return IGMediaInfoResponse.class;
    }

}
