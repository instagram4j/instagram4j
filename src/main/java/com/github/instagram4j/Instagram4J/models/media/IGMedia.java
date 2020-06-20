package com.github.instagram4j.Instagram4J.models.media;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.IGUser;
import com.github.instagram4j.Instagram4J.models.media.IGComment.IGCaption;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl=IGMedia.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({
	@JsonSubTypes.Type(value = IGImageMedia.class),
	@JsonSubTypes.Type(value = IGVideoMedia.class),
	@JsonSubTypes.Type(value = IGCarouselMedia.class)
})
public class IGMedia extends IGBaseModel {
	private long taken_at;
	private long device_timestamp;
	private String media_type;
	private String code;
	private String client_cache_key;
	private int filter_type;
	private IGUser user;
	private IGCaption caption;
	private boolean can_viewer_reshare;
	private List<IGComment> preview_comments;
	private boolean photo_of_you;
	private boolean has_liked;
	private int like_count;
	private int comment_count;
	private boolean can_viewer_save;
}