package com.github.instagram4j.Instagram4J.models.media;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl=IGReelMedia.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
public class IGReelMedia extends IGMedia {
	private int viewer_count;
}
