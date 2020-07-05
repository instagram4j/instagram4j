package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedUserStoryResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGFeedUserStoryRequest extends IGGetRequest<IGFeedUserStoryResponse> {
    @NonNull
    private Long pk;

    @Override
    public String path() {
        return "feed/user/" + pk.toString() + "/story/";
    }

    @Override
    public Class<IGFeedUserStoryResponse> getResponseType() {
        return IGFeedUserStoryResponse.class;
    }

}
