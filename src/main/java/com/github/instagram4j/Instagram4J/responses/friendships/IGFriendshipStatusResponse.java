package com.github.instagram4j.Instagram4J.responses.friendships;

import com.github.instagram4j.Instagram4J.models.friendships.IGFriendship;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFriendshipStatusResponse extends IGResponse {
    private IGFriendship friendship_status;
}
