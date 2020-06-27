package com.github.instagram4j.Instagram4J.models.media.reel.item;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class IGStoryPollsItem extends IGReelMetadataItem {
    @NonNull
    private String question;
    @Builder.Default
    @NonNull
    private List<IGTally> tallies = Arrays.asList(YES, NO);
    public static final IGTally YES = IGTally.builder().text("YES").build(), NO = IGTally.builder().text("NO").build();

    @Override
    public String key() {
        return "story_polls";
    }

    @Data
    @Builder
    public static class IGTally {
        @NonNull
        private String text;
        @Builder.Default
        private int count = 0;
        @Builder.Default
        private double font_size = 35;
    }

}
