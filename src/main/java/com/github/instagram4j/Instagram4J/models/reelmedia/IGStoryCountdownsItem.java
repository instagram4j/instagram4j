package com.github.instagram4j.Instagram4J.models.reelmedia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class IGStoryCountdownsItem extends IGReelMetadataItem {
    @Builder.Default
    private double x = 0.5;
    @Builder.Default
    private double y = 0.5;
    @Builder.Default
    private double z = 0;
    @Builder.Default
    private double width = 0.5;
    @Builder.Default
    private double height = 0.5;
    @Builder.Default
    private double rotation = 0;
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
    private String text;
    private long end_ts;
    private final boolean is_sticker = true;
    
    @Override
    public String key() {
        return "story_countdowns";
    }

}
