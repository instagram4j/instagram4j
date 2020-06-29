package com.github.instagram4j.Instagram4J.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.thread.IGThreadAnimatedMedia;

import lombok.Data;

@Data
@JsonTypeName("animated_media")
public class IGThreadAnimatedMediaItem extends IGThreadItem {
    private IGThreadAnimatedMedia media;
}
