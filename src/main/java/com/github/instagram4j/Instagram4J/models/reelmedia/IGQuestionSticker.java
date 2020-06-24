package com.github.instagram4j.Instagram4J.models.reelmedia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class IGQuestionSticker extends IGReelMetadataItem {
	/**
     * x position of sticker. Range from 0.0 to 1.0. 0.5 is center
     */
    @Builder.Default
    private double x = 0.5;
    /**
     * y position of sticker. Range from 0.0 to 1.0. 0.5 is center
     */
    @Builder.Default
    private double y = 0.5;
    @Builder.Default
    private double z = 0;
    /**
     * width of sticker. Range from 0 to 1.0
     */
    @Builder.Default
    private double width = 0.5;
    /**
     * height of sticker. Range from 0 to 1.0
     */
    @Builder.Default
    private double height = 0.5;
    /**
     * Rotation of sticker
     */
    @Builder.Default
    private double rotation = 0;
    /**
     * Color of text
     */
    @Builder.Default
    private String text_color = "#000000";
    /**
     * Color of background
     */
    @Builder.Default
    private String background_color = "#FFFFFF";
    /**
     * Story Question displayed
     */
    private String question;
    /**
     * Profile Pic URL of the account
     */
    private String profile_pic_url;
    private final boolean viewer_can_interact = false;
    private final boolean is_sticker = true;
    private final String question_type = "text";
    
    public String key() {
    	return "story_questions";
    }
}
