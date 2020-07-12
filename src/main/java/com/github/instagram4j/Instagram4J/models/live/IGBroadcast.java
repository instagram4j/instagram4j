package com.github.instagram4j.Instagram4J.models.live;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.user.IGUser;

import lombok.Data;

@Data
public class IGBroadcast extends IGBaseModel {
    private String id;
    private String dash_playback_url;
    private String dash_abr_playback_url;
    private String dash_live_predictive_playback_url;
    private String broadcast_status;
    private int viewer_count;
    private String cover_frame_url;
    private IGUser broadcast_owner;
    private long published_time;
    private String media_id;
    private String broadcast_message;
}
