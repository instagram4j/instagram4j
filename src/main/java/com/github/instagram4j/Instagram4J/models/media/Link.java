package com.github.instagram4j.Instagram4J.models.media;

import lombok.Data;

@Data
public class Link {
    private String text;
    private LinkContext link_context;
    private String client_context;
    private String mutation_token;

    @Data
    public static class LinkContext {
        private String link_url;
        private String link_title;
        private String link_summary;
        private String link_image_url;
    }
}
