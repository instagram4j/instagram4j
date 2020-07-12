package com.github.instagram4j.Instagram4J.models.media.timeline;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.location.IGLocation;
import com.github.instagram4j.Instagram4J.models.media.IGMedia;
import com.github.instagram4j.Instagram4J.models.media.IGUserTags;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = IGMedia.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = IGTimelineImageMedia.class),
        @JsonSubTypes.Type(value = IGTimelineVideoMedia.class),
        @JsonSubTypes.Type(value = IGTimelineCarouselMedia.class)
})
public class IGTimelineMedia extends IGMedia {
    private List<IGComment> preview_comments;
    private boolean has_liked;
    private int like_count;
    private int comment_count;
    private IGLocation location;
    private IGUserTags usertags;
}
