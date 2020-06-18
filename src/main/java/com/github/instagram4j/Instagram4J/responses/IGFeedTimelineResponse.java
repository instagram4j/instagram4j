package com.github.instagram4j.Instagram4J.responses;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGFeedItem;

import lombok.Data;

@Data
public class IGFeedTimelineResponse extends IGResponse {
	private boolean auto_load_more_enabled;
    private int num_results;
    private String next_max_id;
    private List<IGFeedItem> items;
    private List<IGFeedItem> ranked_items;
    private boolean more_available;
}
