package com.github.instagram4j.instagram4j.requests.news;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.news.NewsInboxResponse;

public class NewsInboxRequest extends IGGetRequest<NewsInboxResponse> {

    @Override
    public String path() {
        return "news/inbox/";
    }

    @Override
    public Class<NewsInboxResponse> getResponseType() {
        return NewsInboxResponse.class;
    }

}
