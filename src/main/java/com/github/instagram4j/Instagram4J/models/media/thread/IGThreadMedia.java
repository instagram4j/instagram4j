package com.github.instagram4j.Instagram4J.models.media.thread;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = IGThreadMedia.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = IGThreadImageMedia.class),
    @JsonSubTypes.Type(value = IGThreadVideoMedia.class)
})
public class IGThreadMedia extends IGBaseModel {
    private String media_type;
    private int original_width;
    private int original_height;
}
