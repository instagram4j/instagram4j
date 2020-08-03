package com.github.instagram4j.instagram4j.models.media.thread;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.instagram4j.models.IGBaseModel;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = ThreadMedia.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ThreadImageMedia.class),
        @JsonSubTypes.Type(value = ThreadVideoMedia.class)
})
public class ThreadMedia extends IGBaseModel {
    private long pk;
    private String id;
    private String media_type;
    private int original_width;
    private int original_height;
}
