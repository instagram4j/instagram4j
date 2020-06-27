package com.github.instagram4j.Instagram4J.models.media;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.timelinemedia.IGComment.IGCaption;
import com.github.instagram4j.Instagram4J.models.user.IGUser;

import lombok.Data;

@Data
public class IGMedia extends IGBaseModel {
    private long taken_at;
    private long device_timestamp;
    private String media_type;
    private String code;
    private String client_cache_key;
    private int filter_type;
    private IGUser user;
    private IGCaption caption;
    private boolean can_viewer_reshare;
    private boolean photo_of_you;
    private boolean can_viewer_save;
}