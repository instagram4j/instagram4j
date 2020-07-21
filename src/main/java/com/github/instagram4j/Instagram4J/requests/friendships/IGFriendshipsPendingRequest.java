package com.github.instagram4j.Instagram4J.requests.friendships;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGUsersFeedResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class IGFriendshipsPendingRequest extends IGGetRequest<IGUsersFeedResponse> {
    private String _max_id;
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", _max_id);
    }
    
    @Override
    public String path() {
        return "friendships/pending/";
    }

    @Override
    public Class<IGUsersFeedResponse> getResponseType() {
        return IGUsersFeedResponse.class;
    }

}
