package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.FeedUserReelsMediaResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedUserReelMediaRequest extends IGGetRequest<FeedUserReelsMediaResponse> {
    @NonNull
    private Long pk;

    @Override
    public String path() {
        return "feed/user/" + pk.toString() + "/reel_media/";
    }

    @Override
    public Class<FeedUserReelsMediaResponse> getResponseType() {
        return FeedUserReelsMediaResponse.class;
    }

}
