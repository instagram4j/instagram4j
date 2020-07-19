package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedTagResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGFeedTagRequest extends IGGetRequest<IGFeedTagResponse> {
    @NonNull
    private String tag;
    private String max_id;

    @Override       
    public String path() {
        return "feed/tag/" + tag + "/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public Class<IGFeedTagResponse> getResponseType() {
        return IGFeedTagResponse.class;
    }

}
