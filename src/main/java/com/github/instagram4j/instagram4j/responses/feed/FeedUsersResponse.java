package com.github.instagram4j.instagram4j.responses.feed;

import java.util.List;

import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class FeedUsersResponse extends IGPaginatedResponse {
    private List<Profile> users;
    private String next_max_id;

    public boolean isMore_available() {
        return next_max_id != null;
    }
}
