package com.github.instagram4j.Instagram4J.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class IGLoggedInUser {
    private long pk;
    private String username;
    private String full_name;
    private boolean is_private;
    private String profile_pic_url;
    private boolean is_verified;
    private boolean has_anonymous_profile_picture;
    private boolean can_boost_post;
    private boolean is_business;
    private int account_type;
    private int professional_conversion_suggested_account_type;
    private boolean is_call_to_action_enabled;
    private boolean can_see_organic_insights;
    private boolean show_insights_terms;
    private int total_igtv_videos;
    private String reel_auto_archive;
    private boolean has_placed_orders;
    private String allowed_commenter_type;
    private Nametag nametag;
    private boolean is_using_unified_inbox_for_direct;
    private long interop_messaging_user_fbid;
    private boolean can_see_primary_country_in_settings;
    private boolean allow_contacts_sync;
    private String phone_number;

    @Getter
    @Setter
    public static class Nametag {
        private int mode;
        private int gradient;
        private String emoji;
        private int selfie_sticker;
    }
}
