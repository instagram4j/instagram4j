package com.github.instagram4j.instagram4j.requests.friendships;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.friendships.FriendshipStatusResponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FriendshipsSetBestiesRequest extends IGPostRequest<FriendshipStatusResponse> {
    private final Long[] _add, _remove;
    
    public FriendshipsSetBestiesRequest(boolean add, Long... pks) {
        this._add = add ? pks : null;
        this._remove = !add ? pks : null;
    }
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new FriendshipsSetBestiesPayload();
    }

    @Override
    public String path() {
        return "friendships/set_besties/";
    }

    @Override
    public Class<FriendshipStatusResponse> getResponseType() {
        return FriendshipStatusResponse.class;
    }
    
    @Data
    @JsonInclude(Include.NON_NULL)
    public class FriendshipsSetBestiesPayload extends IGPayload {
        private Long[] add = _add;
        private Long[] remove = _remove;
    }

}
