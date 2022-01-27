package com.github.instagram4j.instagram4j.requests.friendships;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.friendships.FriendshipStatusResponse;

import java.util.Locale;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FriendshipsActionRequest extends IGPostRequest<FriendshipStatusResponse> {
    @NonNull
    private Long _pk;
    @NonNull
    private FriendshipsAction action;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private long user_id = _pk;
        };
    }

    @Override
    public String path() {
        return String.format("friendships/%s/%s/", action.name().toLowerCase(new Locale("en", "US")), _pk);
    }

    @Override
    public Class<FriendshipStatusResponse> getResponseType() {
        return FriendshipStatusResponse.class;
    }

    public static enum FriendshipsAction {
        BLOCK, UNBLOCK, CREATE, DESTROY, APPROVE, IGNORE, REMOVE_FOLLOWER;
    }
}
