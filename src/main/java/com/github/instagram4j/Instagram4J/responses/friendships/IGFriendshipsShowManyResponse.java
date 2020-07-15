package com.github.instagram4j.Instagram4J.responses.friendships;

import java.util.Map;

import com.github.instagram4j.Instagram4J.models.friendships.IGFriendship;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFriendshipsShowManyResponse extends IGResponse {
    private Map<String, IGFriendship> friendship_statuses;
}
