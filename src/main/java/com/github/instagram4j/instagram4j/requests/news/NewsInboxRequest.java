package com.github.instagram4j.instagram4j.requests.news;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.news.NewsInboxResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NewsInboxRequest extends IGGetRequest<NewsInboxResponse> {
    private Boolean mark_as_seen;
    
    @Override
    public String path() {
        return "news/inbox/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("mark_as_seen", mark_as_seen);
    }

    @Override
    public Class<NewsInboxResponse> getResponseType() {
        return NewsInboxResponse.class;
    }

}
