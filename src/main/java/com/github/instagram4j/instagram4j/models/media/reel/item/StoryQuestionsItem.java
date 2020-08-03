package com.github.instagram4j.instagram4j.models.media.reel.item;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class StoryQuestionsItem extends ReelMetadataItem {
    @Builder.Default
    private String text_color = "#000000";
    @Builder.Default
    private String background_color = "#FFFFFF";
    @NonNull
    private String question;
    private String profile_pic_url;
    @Builder.Default
    private String question_type = "text";

    @Override
    public String key() {
        return "story_questions";
    }
}
