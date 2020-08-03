package com.github.instagram4j.instagram4j.models.media.timeline;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.ImageVersions;
import com.github.instagram4j.instagram4j.models.media.ImageVersionsMeta;

import lombok.Data;

@Data
@JsonTypeName("2")
public class VideoCaraouselItem extends CaraouselItem {
    private ImageVersions image_versions2;
    private List<ImageVersionsMeta> video_versions;
}