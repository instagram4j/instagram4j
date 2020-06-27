package com.github.instagram4j.Instagram4J.models.media.timeline;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = IGCaraouselItem.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = IGImageCaraouselItem.class),
        @JsonSubTypes.Type(value = IGVideoCaraouselItem.class) })
public class IGCaraouselItem extends IGBaseModel {
    private int original_width;
    private int original_height;
    private String media_type;
    private String carousel_parent_id;
}