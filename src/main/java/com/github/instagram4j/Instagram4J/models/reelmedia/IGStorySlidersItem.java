package com.github.instagram4j.Instagram4J.models.reelmedia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class IGStorySlidersItem extends IGReelMetadataItem {
    @Builder.Default
    private double x = 0.5;
    @Builder.Default
    private double y = 0.5;
    @Builder.Default
    private double z = 0;
    @Builder.Default
    private double rotation = 0;
    @Builder.Default
    private double height = 1.0;
    @Builder.Default
    private double width = 1.0;
    private String question;
    @Builder.Default
    private String emoji = "\uD83D\uDE0D";
    @Builder.Default
    private String text_color = "#7F007F";
    @Builder.Default
    private String background_color = "#FFFFFF";
    private final boolean is_pinned = false;
    private final boolean is_hidden = false;

    @Override
    public String key() {
        return "story_sliders";
    }

}
