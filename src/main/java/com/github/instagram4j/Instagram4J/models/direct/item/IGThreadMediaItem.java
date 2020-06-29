package com.github.instagram4j.Instagram4J.models.direct.item;

import com.github.instagram4j.Instagram4J.models.media.thread.IGThreadMedia;

import lombok.Data;

@Data
public class IGThreadMediaItem extends IGThreadItem {
    private IGThreadMedia media;
}
