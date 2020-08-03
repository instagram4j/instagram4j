package com.github.instagram4j.instagram4j.requests.friendships;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.friendships.FriendshipsShowResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FriendshipsShowRequest extends IGGetRequest<FriendshipsShowResponse> {
    @NonNull
    private Long pk;

    @Override
    public String path() {
        return "friendships/show/" + pk + "/";
    }

    @Override
    public Class<FriendshipsShowResponse> getResponseType() {
        return FriendshipsShowResponse.class;
    }

}
