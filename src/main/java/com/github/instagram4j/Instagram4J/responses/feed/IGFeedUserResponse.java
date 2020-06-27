package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFeedUserResponse extends IGResponse {
    private List<IGTimelineMedia> items;
    private String next_max_id;
    private int num_results;
}
