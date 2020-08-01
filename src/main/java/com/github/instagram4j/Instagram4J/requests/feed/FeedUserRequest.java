package com.github.instagram4j.Instagram4J.requests.feed;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.requests.IGPaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.feed.FeedUserResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class FeedUserRequest extends IGGetRequest<FeedUserResponse> implements IGPaginatedRequest<FeedUserResponse> {
    @NonNull
    private Long pk;
    @Setter
    private String max_id;

    @Override
    public String path() {
        return "feed/user/" + pk + "/";
    }

    @Override
    public String getQueryString(IGClient client) {
        return this.mapQueryString("max_id", max_id);
    }

    @Override
    public Class<FeedUserResponse> getResponseType() {
        return FeedUserResponse.class;
    }

}
