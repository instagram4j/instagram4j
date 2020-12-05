package com.github.instagram4j.instagram4j.models.direct.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.reel.ReelMedia;

import lombok.Data;

@Data
@JsonTypeName("reel_share")
public class ThreadReelShareItem extends ThreadItem {
    private String text;
    private String type;
    @JsonProperty("is_reel_persisted")
    private boolean is_reel_persisted;
    private String reel_owner_id;
    private String reel_type;
    private ReelMedia media;
}
