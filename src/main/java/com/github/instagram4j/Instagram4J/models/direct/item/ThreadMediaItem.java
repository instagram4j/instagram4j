package com.github.instagram4j.Instagram4J.models.direct.item;

import com.github.instagram4j.Instagram4J.models.media.thread.ThreadMedia;

import lombok.Data;

@Data
public class ThreadMediaItem extends ThreadItem {
    private ThreadMedia media;
}
