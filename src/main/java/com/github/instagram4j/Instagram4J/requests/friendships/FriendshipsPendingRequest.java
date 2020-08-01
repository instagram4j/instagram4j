package com.github.instagram4j.Instagram4J.requests.friendships;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.requests.PaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.feed.UsersFeedResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class FriendshipsPendingRequest extends IGGetRequest<UsersFeedResponse> implements PaginatedRequest<UsersFeedResponse> {
    @Setter
    private String max_id;
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }
    
    @Override
    public String path() {
        return "friendships/pending/";
    }

    @Override
    public Class<UsersFeedResponse> getResponseType() {
        return UsersFeedResponse.class;
    }

}
