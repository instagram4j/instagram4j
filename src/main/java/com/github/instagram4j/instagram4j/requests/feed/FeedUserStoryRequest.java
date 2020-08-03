package com.github.instagram4j.instagram4j.requests.feed;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedUserStoryResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedUserStoryRequest extends IGGetRequest<FeedUserStoryResponse> {
    @NonNull
    private Long pk;

    @Override
    public String path() {
        return "feed/user/" + pk.toString() + "/story/";
    }

    @Override
    public Class<FeedUserStoryResponse> getResponseType() {
        return FeedUserStoryResponse.class;
    }

}
