package com.github.instagram4j.Instagram4J.requests.friendships;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.friendships.IGFriendshipsShowResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGFriendshipsShowRequest extends IGGetRequest<IGFriendshipsShowResponse> {
    @NonNull
    private Long pk;

    @Override
    public String path() {
        return "friendships/show/" + pk + "/";
    }

    @Override
    public Class<IGFriendshipsShowResponse> getResponseType() {
        return IGFriendshipsShowResponse.class;
    }

}
