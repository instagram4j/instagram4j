package com.github.instagram4j.instagram4j.models.highlights;

import com.github.instagram4j.instagram4j.models.IGBaseModel;
import com.github.instagram4j.instagram4j.models.user.Profile;

import lombok.Data;

@Data
public class Highlight extends IGBaseModel {
    private String id;
    private long latest_reel_media;
    private Profile user;
    private String title;
    private int media_count;
}
