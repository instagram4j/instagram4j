package com.github.instagram4j.Instagram4J.responses.news;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.news.NewsStory;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class NewsInboxResponse extends IGResponse {
    private NewsCounts counts;
    private List<NewsStory> new_stories;
    private List<NewsStory> old_stories;
    
    @Data
    public static class NewsCounts {
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
