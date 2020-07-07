package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.feed.IGReel;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFeedTagResponse extends IGResponse {
    private List<IGTimelineMedia> ranked_items;
    private List<IGTimelineMedia> items;
    private IGReel story;
    private int num_results;
    private String next_max_id;
    private boolean more_available;
}
