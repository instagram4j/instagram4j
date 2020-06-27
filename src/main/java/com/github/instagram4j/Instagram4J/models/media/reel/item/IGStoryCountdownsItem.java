package com.github.instagram4j.Instagram4J.models.media.reel.item;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class IGStoryCountdownsItem extends IGReelMetadataItem {
    @Builder.Default
    private String text_color = "#000000";
    @Builder.Default
    private String start_background_color = "#CA2EE1";
    @Builder.Default
    private String end_background_color = "#5EB1FF";
    @Builder.Default
    private String digit_color = "#7E0091";
    @Builder.Default
    private String digit_card_color = "#FFFFFF";
    @Builder.Default
    private boolean following_enabled = true;
    @NonNull
    private String text;
    private long end_ts;
    
    @Override
    public String key() {
        return "story_countdowns";
    }

}
