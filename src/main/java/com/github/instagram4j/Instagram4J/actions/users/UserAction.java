package com.github.instagram4j.Instagram4J.actions.users;

import java.io.IOException;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.actions.feed.FeedIterable;
import com.github.instagram4j.Instagram4J.models.friendships.Friendship;
import com.github.instagram4j.Instagram4J.models.user.Profile;
import com.github.instagram4j.Instagram4J.requests.friendships.FriendshipsActionRequest.FriendshipsAction;
import com.github.instagram4j.Instagram4J.requests.friendships.FriendshipsActionRequest;
import com.github.instagram4j.Instagram4J.requests.friendships.FriendshipsFeedsRequest;
import com.github.instagram4j.Instagram4J.requests.friendships.FriendshipsFeedsRequest.FriendshipsFeeds;
import com.github.instagram4j.Instagram4J.requests.friendships.FriendshipsShowRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.responses.feed.UsersFeedResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAction {
    @NonNull
    private IGClient client;
    @NonNull
    @Getter
    private Profile user;
    
    public FeedIterable<UsersFeedResponse> followerFeed() {
        return new FeedIterable<>(client, new FriendshipsFeedsRequest(user.getPk(), FriendshipsFeeds.FOLLOWERS));
    }
    
    public FeedIterable<UsersFeedResponse> followingFeed() {
        return new FeedIterable<>(client, new FriendshipsFeedsRequest(user.getPk(), FriendshipsFeeds.FOLLOWING));
    }
    
    public Friendship getFriendship() throws IOException {
        return new FriendshipsShowRequest(user.getPk()).execute(client).getFriendship();
    }
    
    public IGResponse performAction(FriendshipsAction action) throws IOException {
        return new FriendshipsActionRequest(user.getPk(), action).execute(client);
    }
}
