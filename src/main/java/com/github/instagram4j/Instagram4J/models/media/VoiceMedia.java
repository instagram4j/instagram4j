package com.github.instagram4j.Instagram4J.models.media;

import lombok.Data;

@Data
// media type 11
public class VoiceMedia extends Media {
    private Audio audio;
    private String product_type;
    private String seen_user_ids;
    private String view_mode;
    private int seen_count;
    private long replay_expiring_at;
}
