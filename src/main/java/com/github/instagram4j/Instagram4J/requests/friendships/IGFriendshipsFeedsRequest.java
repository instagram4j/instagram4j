package com.github.instagram4j.Instagram4J.requests.friendships;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.friendships.IGFriendshipsFeedsResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGFriendshipsFeedsRequest extends IGGetRequest<IGFriendshipsFeedsResponse> {
    @NonNull
    private Long _id;
    @NonNull
    private IGFriendshipsFeedsAction action;
    private String _max_id;
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", _max_id);
    }
    
    @Override
    public String path() {
        return String.format("friendships/%s/%s/", _id, action.name().toLowerCase());
    }
    @Override
    public Class<IGFriendshipsFeedsResponse> getResponseType() {
        return IGFriendshipsFeedsResponse.class;
    }
    
    public static enum IGFriendshipsFeedsAction {
        FOLLOWERS, FOLLOWING;
    }
}
