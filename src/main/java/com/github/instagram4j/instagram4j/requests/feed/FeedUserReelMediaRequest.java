package com.github.instagram4j.instagram4j.requests.feed;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedUserStoriesMediaResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedUserReelMediaRequest extends IGGetRequest<FeedUserStoriesMediaResponse> {
    @NonNull
    private Long pk;

    @Override
    public String path() {
        return "feed/user/" + pk.toString() + "/reel_media/";
    }

    @Override
    public Class<FeedUserStoriesMediaResponse> getResponseType() {
        return FeedUserStoriesMediaResponse.class;
    }

}
