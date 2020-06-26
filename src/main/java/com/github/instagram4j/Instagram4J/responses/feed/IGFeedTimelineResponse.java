package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.feed.IGFeedItem;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFeedTimelineResponse extends IGResponse {
    private boolean auto_load_more_enabled;
    private int num_results;
    private String next_max_id;
    private List<IGFeedItem> feed_items;
    private boolean more_available;
}