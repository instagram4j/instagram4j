package com.github.instagram4j.Instagram4J.models.reelmedia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class IGReelMentionsItem extends IGReelMetadataItem {
    @Builder.Default
    private double x = 0.5;
    @Builder.Default
    private double y = 0.5;
    @Builder.Default
    private double z = 0;
    @Builder.Default
    private double rotation = 0;
    @NonNull
    private String user_id;
    @Builder.Default
    private double height = 1.0;
    @Builder.Default
    private double width = 1.0;
    private final boolean is_pinned = false;

    @Override
    public String key() {
        return "reel_mentions";
    }

}
