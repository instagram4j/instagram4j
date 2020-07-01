package com.github.instagram4j.Instagram4J.models.direct.item;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.Instagram4J.models.media.thread.IGThreadMedia;

import lombok.Data;

@Data
@JsonTypeName("raven_media")
public class IGThreadRavenMediaItem extends IGThreadItem {
    private IGThreadVisualMedia visual_media;
    
    @Data
    public static class IGThreadVisualMedia {
        private long url_expire_at_secs;
        private int playback_duration_secs;
        private IGThreadMedia media;
        private String seen_user_ids;
        private String view_mode;
        private int seen_count;
    }
}
