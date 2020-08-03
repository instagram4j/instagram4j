package com.github.instagram4j.instagram4j.responses.feed;

import java.util.List;

import com.github.instagram4j.instagram4j.models.media.timeline.TimelineMedia;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class FeedUserResponse extends IGPaginatedResponse {
    private List<TimelineMedia> items;
    private String next_max_id;
    private int num_results;
    
    public boolean isMore_available() {
        return next_max_id != null;
    }
}
