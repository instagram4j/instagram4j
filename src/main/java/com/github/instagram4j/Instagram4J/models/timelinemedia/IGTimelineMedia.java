package com.github.instagram4j.Instagram4J.models.timelinemedia;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.media.IGMedia;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = IGMedia.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = IGImageMedia.class),
        @JsonSubTypes.Type(value = IGVideoMedia.class),
        @JsonSubTypes.Type(value = IGCarouselMedia.class)
})
public class IGTimelineMedia extends IGMedia {
    private List<IGComment> preview_comments;
    private boolean has_liked;
    private int like_count;
    private int comment_count;
}
