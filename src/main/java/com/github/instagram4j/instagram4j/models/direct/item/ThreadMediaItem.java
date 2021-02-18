package com.github.instagram4j.instagram4j.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.thread.ThreadMedia;

import lombok.Data;

@Data
@JsonTypeName("media")
public class ThreadMediaItem extends ThreadItem {
    private ThreadMedia media;
}
