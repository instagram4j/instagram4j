package com.github.instagram4j.instagram4j.requests.feed;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedStoriesTrayResponse;

public class FeedStoriesTrayRequest extends IGGetRequest<FeedStoriesTrayResponse> {

    @Override
    public String path() {
        return "feed/reels_tray/";
    }

    @Override
    public Class<FeedStoriesTrayResponse> getResponseType() {
        return FeedStoriesTrayResponse.class;
    }

}
