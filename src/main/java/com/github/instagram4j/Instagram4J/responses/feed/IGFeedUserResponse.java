package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class IGFeedUserResponse extends IGPaginatedResponse {
    private List<IGTimelineMedia> items;
    private String next_max_id;
    private int num_results;
    
    public boolean isMore_available() {
        return next_max_id != null;
    }
}
