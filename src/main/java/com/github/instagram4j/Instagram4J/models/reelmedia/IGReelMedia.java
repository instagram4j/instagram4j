package com.github.instagram4j.Instagram4J.models.reelmedia;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.media.IGMedia;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl=IGReelMedia.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({
	@JsonSubTypes.Type(value = IGReelImageMedia.class)
})
public class IGReelMedia extends IGMedia {
	private int viewer_count;
}
