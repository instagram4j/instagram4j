package com.github.instagram4j.instagram4j.models.media;

import lombok.Data;

@Data
public class Viewer {
    private long pk;
    private String username;
    private String full_name;
    private boolean is_private;
    private String profile_pic_url;
    private String profile_pic_id;
    private boolean is_verified;
}
