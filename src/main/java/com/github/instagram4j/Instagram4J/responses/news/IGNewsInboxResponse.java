package com.github.instagram4j.Instagram4J.responses.news;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.news.IGNewsStory;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGNewsInboxResponse extends IGResponse {
    private IGNewsCounts counts;
    private List<IGNewsStory> new_stories;
    private List<IGNewsStory> old_stories;
    
    @Data
    public static class IGNewsCounts {
        private int likes;
        private int comments;
        private int shopping_notification;
        private int usertags;
        private int relationships;
        private int campaign_notification;
        private int comment_likes;
        private int photos_of_you;
        private int requests;
    }
}
