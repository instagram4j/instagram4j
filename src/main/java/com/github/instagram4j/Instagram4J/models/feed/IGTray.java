package com.github.instagram4j.Instagram4J.models.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.media.reel.IGReelMedia;
import com.github.instagram4j.Instagram4J.models.user.IGUser;

import lombok.Data;

@Data
public class IGTray extends IGBaseModel {
    private long latest_reel_media;
    private long expiring_at;
    private int seen;
    private boolean can_reply;
    private String reel_type;
    private boolean is_sensitive_vertical_ad;
    private IGUser user;
    private int ranked_position;
    private int seen_ranked_position;
    private boolean muted;
    private int media_count;
    private long[] media_ids;
    private List<IGReelMedia> items;
}
