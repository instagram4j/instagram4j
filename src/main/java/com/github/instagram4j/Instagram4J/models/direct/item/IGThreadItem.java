package com.github.instagram4j.Instagram4J.models.direct.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
@JsonTypeInfo(defaultImpl = IGThreadItem.class, use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "item_type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = IGThreadTextItem.class),
        @JsonSubTypes.Type(value = IGThreadMediaShareItem.class),
        @JsonSubTypes.Type(value = IGThreadStoryShareItem.class),
        @JsonSubTypes.Type(value = IGThreadLinkItem.class),
        @JsonSubTypes.Type(value = IGThreadVoiceMediaItem.class),
        @JsonSubTypes.Type(value = IGThreadMediaItem.class),
        @JsonSubTypes.Type(value = IGThreadProfileItem.class),
        @JsonSubTypes.Type(value = IGThreadRavenMediaItem.class)
})
public class IGThreadItem extends IGBaseModel {
    private String item_id;
    private long user_id;
    private long timestamp;
    private String item_type;
    private String client_context;
    private boolean show_forward_attribution;
}
