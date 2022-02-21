package com.github.instagram4j.instagram4j.models.media.stories.item;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class StoryHashtagsItem extends StoriesMetadataItem {
    @NonNull
    private String tag_name;

    @Override
    public String key() {
        return "story_hashtags";
    }

}
