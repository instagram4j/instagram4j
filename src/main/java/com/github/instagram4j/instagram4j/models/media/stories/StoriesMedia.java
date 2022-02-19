package com.github.instagram4j.instagram4j.models.media.stories;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.instagram4j.models.media.Media;
import com.github.instagram4j.instagram4j.models.media.Viewer;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = StoriesMedia.class, use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StoriesImageMedia.class),
        @JsonSubTypes.Type(value = StoriesVideoMedia.class)
})
public class StoriesMedia extends Media {
    private int viewer_count;
    private int total_viewer_count;
    private List<Viewer> viewers;
}
