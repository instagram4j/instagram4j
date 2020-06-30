package com.github.instagram4j.Instagram4J.models.user;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
public class IGProfile extends IGBaseModel {
    private String username;
    private String full_name;
    private boolean is_private;
    private String profile_pic_url;
    private String profile_pic_id;
    private boolean is_verified;
    private boolean has_anonymous_profile_picture;
}
