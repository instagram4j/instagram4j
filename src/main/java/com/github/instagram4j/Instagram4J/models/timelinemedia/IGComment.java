package com.github.instagram4j.Instagram4J.models.timelinemedia;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.IGUser;

import lombok.Data;

@Data
public class IGComment extends IGBaseModel {
    private long user_id;
    private String text;
    private int type;
    private long created_at;
    private long created_at_utc;
    private String content_type;
    private String status;
    private int bit_flags;
    private IGUser user;
    private boolean did_report_as_spam;
    private boolean share_enabled;
    private long media_id;
    private int comment_like_count;

    public static class IGCaption extends IGComment {
    }
}