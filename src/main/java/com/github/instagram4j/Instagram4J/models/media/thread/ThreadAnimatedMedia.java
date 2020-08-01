package com.github.instagram4j.Instagram4J.models.media.thread;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.user.User;

import lombok.Data;

@Data
public class ThreadAnimatedMedia extends IGBaseModel {
    private ThreadImages images;
    private boolean is_random;
    private boolean is_sticker;
    private User user;

    @Data
    public static class ThreadImages {
        private FixedHeight fixed_height;
    }

    @Data
    public static class FixedHeight {
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
