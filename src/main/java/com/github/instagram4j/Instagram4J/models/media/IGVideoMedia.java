package com.github.instagram4j.Instagram4J.models.media;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;

//media_type 2
@Data
@JsonTypeName("2")
public class IGVideoMedia extends IGMedia {
	private List<IGImageVideoMeta> video_versions;
	private long video_duration;
	private boolean has_audio;
	private int original_width;
	private int original_height;
	private int view_count;
}