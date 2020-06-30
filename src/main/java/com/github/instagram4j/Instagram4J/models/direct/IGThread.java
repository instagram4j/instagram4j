package com.github.instagram4j.Instagram4J.models.direct;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.direct.item.IGThreadItem;
import com.github.instagram4j.Instagram4J.models.user.IGProfile;
import com.github.instagram4j.Instagram4J.models.user.IGUser;

import lombok.Data;

@Data
public class IGThread extends IGBaseModel {
    private String thread_id;
    private String thread_v2_id;
    private String id = thread_id;
    private List<IGProfile> users;
    private List<IGProfile> left_users;
    private List<String> admin_user_ids;
    private List<IGThreadItem> items;
    private long last_activity_at;
    private boolean muted;
    private boolean is_pin;
    private boolean named;
    private boolean pending;
    private String thread_type;
    private long viewer_id;
    private String thread_title;
    private boolean is_group;
    private boolean approval_required_for_new_members;
    private int read_state;
    private long last_non_sender_item_at;
    private long assigned_admin_id;
    private boolean shh_mode_enabled;
    private IGUser inviter;
    private boolean has_older;
    private boolean has_newer;
    private String newest_cursor;
    private String oldest_cursor;
    private String next_cursor;
    private String prev_cursor;
    private boolean is_spam;
    private IGThreadItem last_permanent_item;
}
