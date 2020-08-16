package com.github.instagram4j.instagram4j.models.media.reel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.instagram4j.models.media.Media;
import com.github.instagram4j.instagram4j.models.media.Viewer;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = ReelMedia.class, use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ReelImageMedia.class),
        @JsonSubTypes.Type(value = ReelVideoMedia.class)
})
public class ReelMedia extends Media {
    private int viewer_count;
    private int total_viewer_count;
    private List<Viewer> viewers;
}
