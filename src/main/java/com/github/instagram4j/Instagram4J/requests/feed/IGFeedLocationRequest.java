package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedLocationResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGFeedLocationRequest extends IGGetRequest<IGFeedLocationResponse> {
    @NonNull
    private Long location;
    private String max_id;
    
    @Override
    public String path() {
        return "feed/location/" + location + "/";
    }
    
    @Override
    public String getQueryString() {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public Class<IGFeedLocationResponse> getResponseType() {
        return IGFeedLocationResponse.class;
    }

}
