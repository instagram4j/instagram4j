package com.github.instagram4j.Instagram4J.responses.friendships;

import java.util.Map;

import com.github.instagram4j.Instagram4J.models.friendships.Friendship;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class FriendshipsShowManyResponse extends IGResponse {
    private Map<String, Friendship> friendship_statuses;
}
