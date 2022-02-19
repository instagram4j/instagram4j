package com.github.instagram4j.instagram4j.responses.feed;

import java.util.List;
import com.github.instagram4j.instagram4j.models.feed.Stories;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineMedia;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

@Data
public class FeedTagResponse extends IGResponse implements IGPaginatedResponse {
    private List<TimelineMedia> ranked_items;
    private List<TimelineMedia> items;
    private Stories story;
    private int num_results;
    private String next_max_id;
    private boolean more_available;
}
