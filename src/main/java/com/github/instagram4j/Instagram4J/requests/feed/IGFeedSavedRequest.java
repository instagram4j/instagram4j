package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedSavedResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class IGFeedSavedRequest extends IGGetRequest<IGFeedSavedResponse> {
    private String _max_id;
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", _max_id);
    }

    @Override
    public String path() {
        return "feed/saved/";
    }

    @Override
    public Class<IGFeedSavedResponse> getResponseType() {
        return IGFeedSavedResponse.class;
    }
    
}
