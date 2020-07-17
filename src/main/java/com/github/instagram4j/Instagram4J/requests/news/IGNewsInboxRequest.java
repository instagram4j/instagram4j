package com.github.instagram4j.Instagram4J.requests.news;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.news.IGNewsInboxResponse;

public class IGNewsInboxRequest extends IGGetRequest<IGNewsInboxResponse> {

    @Override
    public String path() {
        return "news/inbox/";
    }

    @Override
    public Class<IGNewsInboxResponse> getResponseType() {
        return IGNewsInboxResponse.class;
    }

}
