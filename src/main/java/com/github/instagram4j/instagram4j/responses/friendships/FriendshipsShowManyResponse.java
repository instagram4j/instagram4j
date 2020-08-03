package com.github.instagram4j.instagram4j.responses.friendships;

import java.util.Map;

import com.github.instagram4j.instagram4j.models.friendships.Friendship;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class FriendshipsShowManyResponse extends IGResponse {
    private Map<String, Friendship> friendship_statuses;
}
