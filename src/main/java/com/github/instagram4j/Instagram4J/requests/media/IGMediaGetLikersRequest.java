package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGUsersFeedResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGMediaGetLikersRequest extends IGGetRequest<IGUsersFeedResponse> {
    @NonNull
    private String _id;

    @Override
    public String path() {
        return "media/" + _id + "/likers/";
    }
    
    @Override
    public Class<IGUsersFeedResponse> getResponseType() {
        return IGUsersFeedResponse.class;
    }
}
