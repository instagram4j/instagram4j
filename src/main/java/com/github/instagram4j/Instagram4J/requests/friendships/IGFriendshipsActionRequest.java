package com.github.instagram4j.Instagram4J.requests.friendships;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.friendships.IGFriendshipStatusResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGFriendshipsActionRequest extends IGPostRequest<IGFriendshipStatusResponse> {
    @NonNull
    private Long _pk;
    @NonNull
    private IGFriendshipsAction action;

    @Override
    protected IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            private long user_id = _pk;
        };
    }

    @Override
    public String path() {
        return String.format("friendships/%s/%s/", action.name().toLowerCase(), _pk);
    }

    @Override
    public Class<IGFriendshipStatusResponse> getResponseType() {
        return IGFriendshipStatusResponse.class;
    }

    public static enum IGFriendshipsAction {
        BLOCK, UNBLOCK, CREATE, DESTROY, APPROVE, IGNORE, REMOVE_FOLLOWER;
    }
}
