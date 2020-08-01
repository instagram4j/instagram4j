package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.requests.PaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.feed.FeedLocationResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class FeedLocationRequest extends IGGetRequest<FeedLocationResponse> implements PaginatedRequest<FeedLocationResponse> {
    @NonNull
    private Long location;
    @Setter
    private String max_id;
    
    @Override
    public String path() {
        return "feed/location/" + location + "/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public Class<FeedLocationResponse> getResponseType() {
        return FeedLocationResponse.class;
    }

}
