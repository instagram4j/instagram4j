package com.github.instagram4j.instagram4j.models.media;

import lombok.Data;

@Data
public class VideoVersionsMeta implements Meta {
    private int height;
    private int width;
    private String id;
    private String type;
    private String url;
}
