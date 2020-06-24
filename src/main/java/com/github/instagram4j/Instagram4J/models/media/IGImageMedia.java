package com.github.instagram4j.Instagram4J.models.media;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

@Data
@JsonTypeName("1")
public class IGImageMedia extends IGMedia {
    private IGImageVersions image_versions2;
    private List<IGImageVideoMeta> candidates;
    private long video_duration;
    private boolean has_audio;
    private int original_width;
    private int original_height;
    private int view_count;
}