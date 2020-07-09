package com.github.instagram4j.Instagram4J.responses.media;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMediaInfoResponse extends IGResponse {
    private List<IGTimelineMedia> items;
    private int num_results;
    private boolean more_available;
}
