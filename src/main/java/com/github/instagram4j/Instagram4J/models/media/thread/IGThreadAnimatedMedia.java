package com.github.instagram4j.Instagram4J.models.media.thread;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.user.IGUser;

import lombok.Data;

@Data
public class IGThreadAnimatedMedia extends IGBaseModel {
    private IGThreadImages images;
    private boolean is_random;
    private boolean is_sticker;
    private IGUser user;
    
    @Data
    public static class IGThreadImages {
        private IGFixedHeight fixed_height;
    }
    
    @Data
    public static class IGFixedHeight {
        private String height;
        private String mp4;
        private String mp4_size;
        private String size;
        private String url;
        private String webp;
        private String webp_size;
        private String width;
    }
}
