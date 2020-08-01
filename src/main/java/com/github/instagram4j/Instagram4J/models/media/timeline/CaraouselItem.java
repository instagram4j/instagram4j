package com.github.instagram4j.Instagram4J.models.media.timeline;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = CaraouselItem.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "media_type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = ImageCaraouselItem.class),
        @JsonSubTypes.Type(value = VideoCaraouselItem.class) })
public class CaraouselItem extends IGBaseModel {
    private int original_width;
    private int original_height;
    private String media_type;
    private String carousel_parent_id;
}