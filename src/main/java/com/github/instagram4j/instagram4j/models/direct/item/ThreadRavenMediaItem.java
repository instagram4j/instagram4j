package com.github.instagram4j.instagram4j.models.direct.item;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.instagram4j.instagram4j.models.media.thread.ThreadMedia;
import lombok.Data;

@Data
@JsonTypeName("raven_media")
public class ThreadRavenMediaItem extends ThreadItem {
    private ThreadVisualMedia visual_media;

    @Data
    public static class ThreadVisualMedia {
        private long url_expire_at_secs;
        private int playback_duration_secs;
        private ThreadMedia media;
        private List<String> seen_user_ids;
        private String view_mode;
        private int seen_count;
    }
}
