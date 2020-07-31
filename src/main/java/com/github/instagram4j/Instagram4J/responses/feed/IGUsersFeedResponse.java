package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.user.IGProfile;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class IGUsersFeedResponse extends IGPaginatedResponse {
    private List<IGProfile> users;
    private String next_max_id;
    
    public boolean isMore_available() {
        return next_max_id != null;
    }
}
