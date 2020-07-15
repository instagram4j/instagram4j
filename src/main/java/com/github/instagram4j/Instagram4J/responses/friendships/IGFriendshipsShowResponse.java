package com.github.instagram4j.Instagram4J.responses.friendships;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.Instagram4J.models.friendships.IGFriendship;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFriendshipsShowResponse extends IGResponse {
    @JsonUnwrapped
    private IGFriendship friendship;
}
