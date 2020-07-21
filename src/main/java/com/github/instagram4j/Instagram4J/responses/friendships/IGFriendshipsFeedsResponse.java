package com.github.instagram4j.Instagram4J.responses.friendships;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.user.IGProfile;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFriendshipsFeedsResponse extends IGResponse {
    private List<IGProfile> users;
    private String next_max_id;
}
