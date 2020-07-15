package com.github.instagram4j.Instagram4J.requests.friendships;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Getter;
import lombok.NonNull;

public class IGFriendshipsShowManyRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _user_ids;
    
    public IGFriendshipsShowManyRequest(Long... user_pks) {
        this._user_ids = Stream.of(user_pks).map(Object::toString).collect(Collectors.joining(","));
    }

    @Override
    protected IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            private String user_ids = _user_ids;
        };
    }

    @Override
    public String path() {
        return "friendships/show_many/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
}
