package com.github.instagram4j.Instagram4J.requests.news;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.news.NewsInboxResponse;

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
