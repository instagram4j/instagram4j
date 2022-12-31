package com.github.instagram4j.instagram4j.models.user;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

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
    private String public_email;
    private String contact_phone_number;
    private String public_phone_country_code;
    private String public_phone_number;
    private String business_contact_method;
    private String business_category_name;
    private String category_name;
    private String category;
    private String address_street;
    private String city_id;
    private String city_name;
    private String latitude;
    private String longitude;
    private String zip;
    private String instagram_location_id;

    @Data
    public static class ProfilePic implements Serializable {
        public String url;
        public int width;
        public int height;
    }
}
