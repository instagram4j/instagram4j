package com.github.instagram4j.Instagram4J.models.media;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

@Data
@JsonTypeName("2")
public class IGVideoCaraouselItem extends IGCaraouselItem {
	private IGImageVersions image_versions2;
	private List<IGImageVideoMeta> video_versions;
}