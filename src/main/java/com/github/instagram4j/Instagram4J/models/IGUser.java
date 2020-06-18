package com.github.instagram4j.Instagram4J.models;

import java.util.List;

import lombok.Data;

@Data
public class IGUser {
	public boolean is_private;
    public boolean is_verified;
    public String username;
    public boolean has_chaining;
    public boolean is_business;
    public int media_count;
    public String profile_pic_id;
    public String external_url;
    public String full_name;
    public boolean has_biography_translation;
    public boolean has_anonymous_profile_picture;
    public boolean is_favorite;
    public String public_phone_country_code;
    public String public_phone_number;
    public String public_email;
    public long pk;
    public int geo_media_count;
    public int usertags_count;
    public String profile_pic_url;
    public String address_street;
    public String city_name;
    public String zip;
    public String direct_messaging;
    public String business_contact_method;
    public String biography;
    public int follower_count;
    public List<IGProfilePic> hd_profile_pic_versions;
    public IGProfilePic hd_profile_pic_url_info;
    public String external_lynx_url;
    public int following_count;
    public float latitude;
    public float longitude;
    public String category;
    
    @Data
    public static class IGProfilePic {
    	public String url;
        public int width;
        public int height;
    }
}
