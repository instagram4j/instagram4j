package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.requests.PaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.feed.FeedSavedResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FeedSavedRequest extends IGGetRequest<FeedSavedResponse> implements PaginatedRequest<FeedSavedResponse> {
    @Setter
    private String max_id;
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public String path() {
        return "feed/saved/";
    }

    @Override
    public Class<FeedSavedResponse> getResponseType() {
        return FeedSavedResponse.class;
    }
    
}
