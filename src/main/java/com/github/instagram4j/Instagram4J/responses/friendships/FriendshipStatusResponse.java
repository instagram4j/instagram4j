package com.github.instagram4j.Instagram4J.responses.friendships;

import com.github.instagram4j.Instagram4J.models.friendships.Friendship;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class FriendshipStatusResponse extends IGResponse {
    private Friendship friendship_status;
}
