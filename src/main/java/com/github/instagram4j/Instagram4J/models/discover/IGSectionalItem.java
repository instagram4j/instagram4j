package com.github.instagram4j.Instagram4J.models.discover;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;


@Data
@JsonTypeInfo(defaultImpl = IGSectionalItem.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "layout_type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = IGSectionalMediaGridItem.class)
})
public class IGSectionalItem extends IGBaseModel {
    private String layout_type;
    private String feed_type;
}
