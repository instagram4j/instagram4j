package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedUserResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGFeedUserRequest extends IGGetRequest<IGFeedUserResponse> {
    @NonNull
    private Long pk;
    private String max_id;
    
    @Override
    public String path() {
        return "/feed/user/" + pk + "/";
    }
    
    @Override
    public String getQueryString() {
        return this.mapQueryString("max_id", max_id);
    }
    @Override
    public Class<IGFeedUserResponse> getResponseType() {
        return IGFeedUserResponse.class;
    }

}
