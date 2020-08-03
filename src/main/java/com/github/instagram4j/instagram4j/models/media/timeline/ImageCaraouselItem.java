package com.github.instagram4j.instagram4j.models.media.timeline;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.ImageVersions;

import lombok.Data;

//media_type 1
@Data
@JsonTypeName("1")
public class ImageCaraouselItem extends CaraouselItem {
    private ImageVersions image_versions2;
}