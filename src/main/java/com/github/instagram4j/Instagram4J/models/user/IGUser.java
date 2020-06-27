package com.github.instagram4j.Instagram4J.models.user;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
public class IGUser extends IGBaseModel {
    private String username;
    private boolean is_private;
    private boolean is_verified;
    private boolean is_business;
    private int media_count;
    private String profile_pic_url;
    private String full_name;
    private int follower_count;
    private int following_count;
    private String biography;
    private String external_url;
    private List<IGProfilePic> hd_profile_pic_versions;
    private IGProfilePic hd_profile_pic_url_info;
    private int account_type;

    @Data
    public static class IGProfilePic {
        public String url;
        public int width;
        public int height;
    }
}
