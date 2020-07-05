package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedUserReelsMediaResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGFeedUserReelMediaRequest extends IGGetRequest<IGFeedUserReelsMediaResponse> {
    @NonNull
    private String pk;

    @Override
    public String path() {
        return "feed/user/" + pk + "/reel_media/";
    }

    @Override
    public Class<IGFeedUserReelsMediaResponse> getResponseType() {
        return IGFeedUserReelsMediaResponse.class;
    }

}
