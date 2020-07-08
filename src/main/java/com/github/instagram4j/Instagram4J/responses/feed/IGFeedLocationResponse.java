package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.feed.IGReel;
import com.github.instagram4j.Instagram4J.models.location.IGLocation;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFeedLocationResponse extends IGResponse {
    private List<IGTimelineMedia> ranked_items;
    private List<IGTimelineMedia> items;
    private IGReel story;
    private IGLocation location;
    private int num_results;
    private int media_count;
    private String next_max_id;
    private boolean more_available;
}
