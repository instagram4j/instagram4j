package com.github.instagram4j.Instagram4J.models.media.timeline;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.IGImageVersions;
import com.github.instagram4j.Instagram4J.models.media.IGImageVersionsMeta;

import lombok.Data;

@Data
@JsonTypeName("1")
public class IGImageMedia extends IGTimelineMedia {
    private IGImageVersions image_versions2;
    private List<IGImageVersionsMeta> candidates;
    private long video_duration;
    private boolean has_audio;
    private int original_width;
    private int original_height;
    private int view_count;
}