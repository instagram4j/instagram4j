package com.github.instagram4j.Instagram4J.requests.feed;

import java.net.URLEncoder;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedTagResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGFeedTagRequest extends IGGetRequest<IGFeedTagResponse> {
    @NonNull
    private String tag;
    private String max_id;

    @Override
    @SneakyThrows
    public String path() {
        return "feed/tag/" + URLEncoder.encode(tag, "utf-8") + "/";
    }
    
    @Override
    public String getQueryString() {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public Class<IGFeedTagResponse> getResponseType() {
        return IGFeedTagResponse.class;
    }

}
