package com.github.instagram4j.Instagram4J.models.timelinemedia;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.IGImageVersions;
import com.github.instagram4j.Instagram4J.models.media.IGImageVersionsMeta;

import lombok.Data;

@Data
@JsonTypeName("2")
public class IGVideoCaraouselItem extends IGCaraouselItem {
    private IGImageVersions image_versions2;
    private List<IGImageVersionsMeta> video_versions;
}