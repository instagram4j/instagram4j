package com.github.instagram4j.instagram4j.actions.users;

import java.io.IOException;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.FeedIterator;
import com.github.instagram4j.instagram4j.models.friendships.Friendship;
import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsFeedsRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsShowRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest.FriendshipsAction;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsFeedsRequest.FriendshipsFeeds;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.feed.FeedUsersResponse;

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

    public FeedIterator<FeedUsersResponse> followersFeed() {
        return new FeedIterator<>(client, new FriendshipsFeedsRequest(user.getPk(), FriendshipsFeeds.FOLLOWERS));
    }

    public FeedIterator<FeedUsersResponse> followingFeed() {
        return new FeedIterator<>(client, new FriendshipsFeedsRequest(user.getPk(), FriendshipsFeeds.FOLLOWING));
    }

    public Friendship getFriendship() throws IOException {
        return new FriendshipsShowRequest(user.getPk()).execute(client).getFriendship();
    }

    public IGResponse performAction(FriendshipsAction action) throws IOException {
        return new FriendshipsActionRequest(user.getPk(), action).execute(client);
    }
}
