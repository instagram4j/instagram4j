package com.github.instagram4j.Instagram4J.models.media;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

//media_type 1
@Data
@JsonTypeName("1")
public class IGImageCaraouselItem extends IGCaraouselItem {
    private IGImageVersions image_versions2;
}