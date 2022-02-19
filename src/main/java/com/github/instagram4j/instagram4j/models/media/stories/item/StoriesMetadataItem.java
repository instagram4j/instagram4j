package com.github.instagram4j.instagram4j.models.media.stories.item;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StoriesMetadataItem {
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
    private double rotation = 0;
    private int is_pinned;
    private int is_hidden;
    private int is_sticker;

    public abstract String key();
}
