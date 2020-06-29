package com.github.instagram4j.Instagram4J.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.reel.IGReelMedia;

import lombok.Data;

@Data
@JsonTypeName("story_share")
public class IGThreadStoryShareItem extends IGThreadItem {
    private String text;
    private String story_share_type;
    private boolean is_reel_persisted;
    private String reel_id;
    private String reel_Type;
    private IGReelMedia media;
}
