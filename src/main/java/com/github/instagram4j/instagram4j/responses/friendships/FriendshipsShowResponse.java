package com.github.instagram4j.instagram4j.responses.friendships;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.instagram4j.models.friendships.Friendship;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class FriendshipsShowResponse extends IGResponse {
    @JsonUnwrapped
    private Friendship friendship;
}
