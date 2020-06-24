package com.github.instagram4j.Instagram4J.models.reelmedia;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class IGStoryPollsItem extends IGReelMetadataItem {
    private String question;
    @Builder.Default
    private List<IGTally> tallies = Arrays.asList(YES, NO);
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
    private final String poll_id = "polling_sticker_vibrant";
    private final boolean is_pinned = false;
    private final boolean is_shared_result = false;
    public static final IGTally YES = IGTally.builder().text("YES").build(), NO = IGTally.builder().text("NO").build();

    @Override
    public String key() {
        return "story_polls";
    }

    @Data
    @Builder
    public static class IGTally {
        private String text;
        private final int count = 0;
        @Builder.Default
        private double font_size = 35;
    }

}
