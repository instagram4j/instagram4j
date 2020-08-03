package com.github.instagram4j.instagram4j.responses.feed;

import java.util.List;

import com.github.instagram4j.instagram4j.models.feed.Reel;
import com.github.instagram4j.instagram4j.models.location.Location;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineMedia;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class FeedLocationResponse extends IGPaginatedResponse {
    private List<TimelineMedia> ranked_items;
    private List<TimelineMedia> items;
    private Reel story;
    private Location location;
    private int num_results;
    private int media_count;
    private String next_max_id;
    private boolean more_available;
}
