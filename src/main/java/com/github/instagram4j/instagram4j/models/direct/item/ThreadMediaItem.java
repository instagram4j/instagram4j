package com.github.instagram4j.instagram4j.models.direct.item;

import com.github.instagram4j.instagram4j.models.media.thread.ThreadMedia;

import lombok.Data;

@Data
public class ThreadMediaItem extends ThreadItem {
    private ThreadMedia media;
}
