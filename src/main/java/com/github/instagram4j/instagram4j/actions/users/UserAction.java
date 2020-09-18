package com.github.instagram4j.instagram4j.actions.users;

import java.util.concurrent.CompletableFuture;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.FeedIterable;
import com.github.instagram4j.instagram4j.models.friendships.Friendship;
import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest.FriendshipsAction;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsFeedsRequest;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsFeedsRequest.FriendshipsFeeds;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsShowRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedUsersResponse;
import com.github.instagram4j.instagram4j.responses.friendships.FriendshipStatusResponse;
import com.github.instagram4j.instagram4j.responses.friendships.FriendshipsShowResponse;

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

    public FeedIterable<FeedUsersResponse> followersFeed() {
        return new FeedIterable<>(client, () -> {
            return new FriendshipsFeedsRequest(user.getPk(), FriendshipsFeeds.FOLLOWERS);
        });
    }

    public FeedIterable<FeedUsersResponse> followingFeed() {
        return new FeedIterable<>(client, () -> {
            return new FriendshipsFeedsRequest(user.getPk(), FriendshipsFeeds.FOLLOWING);
        });
    }

    public CompletableFuture<Friendship> getFriendship() {
        return new FriendshipsShowRequest(user.getPk()).execute(client)
                .thenApply(FriendshipsShowResponse::getFriendship);
    }

    public CompletableFuture<FriendshipStatusResponse> action(FriendshipsAction action) {
        return new FriendshipsActionRequest(user.getPk(), action).execute(client);
    }
}
