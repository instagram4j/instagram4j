package com.github.instagram4j.Instagram4J.models.direct;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.direct.item.IGThreadRavenMediaItem;

import lombok.Data;

@Data
public class IGDirectStory {
    private List<IGThreadRavenMediaItem> items;
    private long last_activity_at;
    private boolean has_newer;
    private String next_cursor;
}
