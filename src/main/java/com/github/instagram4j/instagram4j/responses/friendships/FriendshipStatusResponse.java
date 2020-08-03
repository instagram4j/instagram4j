package com.github.instagram4j.instagram4j.responses.friendships;

import com.github.instagram4j.instagram4j.models.friendships.Friendship;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class FriendshipStatusResponse extends IGResponse {
    private Friendship friendship_status;
}
