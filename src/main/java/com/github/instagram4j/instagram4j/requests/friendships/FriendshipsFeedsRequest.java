package com.github.instagram4j.instagram4j.requests.friendships;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedUsersResponse;

import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class FriendshipsFeedsRequest extends IGGetRequest<FeedUsersResponse>
        implements IGPaginatedRequest {
    @NonNull
    private Long _id;
    @NonNull
    private FriendshipsFeeds action;
    @Setter
    private String max_id;

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public String path() {
        return String.format("friendships/%s/%s/", _id, action.name().toLowerCase(new Locale("en", "US")));
    }

    @Override
    public Class<FeedUsersResponse> getResponseType() {
        return FeedUsersResponse.class;
    }

    public static enum FriendshipsFeeds {
        FOLLOWERS, FOLLOWING;
    }
}
