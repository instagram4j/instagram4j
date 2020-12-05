package com.github.instagram4j.instagram4j.models.user;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User extends Profile {
    @JsonProperty("is_business")
    private boolean is_business;
    private int media_count;
    private int follower_count;
    private int following_count;
    private String biography;
    private String external_url;
    private List<ProfilePic> hd_profile_pic_versions;
    private ProfilePic hd_profile_pic_url_info;
    private int account_type;

    @Data
    public static class ProfilePic {
        public String url;
        public int width;
        public int height;
    }
}
