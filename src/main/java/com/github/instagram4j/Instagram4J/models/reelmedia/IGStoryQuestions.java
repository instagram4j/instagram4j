package com.github.instagram4j.Instagram4J.models.reelmedia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class IGStoryQuestions extends IGReelMetadataItem {
    @Builder.Default private double x = 0.5;
    @Builder.Default private double y = 0.5;
    @Builder.Default private double z = 0;
    @Builder.Default private double width = 0.5;
    @Builder.Default private double height = 0.5;
    @Builder.Default private double rotation = 0;
    @Builder.Default private String text_color = "#000000";
    @Builder.Default private String background_color = "#FFFFFF";
    private String question;
    private String profile_pic_url;
    private final boolean viewer_can_interact = false;
    private final boolean is_sticker = true;
    private final String question_type = "text";
    
    @Override
    public String key() {
    	return "story_questions";
    }
}
