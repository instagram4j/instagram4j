package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedReelsTrayResponse;

public class IGFeedReelsTrayRequest extends IGGetRequest<IGFeedReelsTrayResponse> {

    @Override
    public String path() {
        return "feed/reels_tray/";
    }

    @Override
    public Class<IGFeedReelsTrayResponse> getResponseType() {
        return IGFeedReelsTrayResponse.class;
    }

}
