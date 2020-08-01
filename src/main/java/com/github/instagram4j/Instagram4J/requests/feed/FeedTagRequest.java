package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.requests.PaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.feed.FeedTagResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class FeedTagRequest extends IGGetRequest<FeedTagResponse> implements PaginatedRequest<FeedTagResponse> {
    @NonNull
    private String tag;
    @Setter
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
    public Class<FeedTagResponse> getResponseType() {
        return FeedTagResponse.class;
    }

}
