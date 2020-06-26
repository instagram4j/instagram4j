package com.github.instagram4j.Instagram4J.models.timelinemedia;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.IGImageVersions;

import lombok.Data;

//media_type 1
@Data
@JsonTypeName("1")
public class IGImageCaraouselItem extends IGCaraouselItem {
    private IGImageVersions image_versions2;
}