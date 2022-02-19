package com.github.instagram4j.instagram4j.requests.feed;

import java.util.stream.Stream;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;

import com.github.instagram4j.instagram4j.responses.feed.FeedStoriesMediaResponse;
import lombok.Getter;

public class FeedReelsMediaRequest extends IGPostRequest<FeedStoriesMediaResponse> {
    private String[] _ids;

    public FeedReelsMediaRequest(Object... ids) {
        _ids = Stream.of(ids).map(Object::toString).toArray(String[]::new);
    }

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private String[] user_ids = _ids;
        };
    }

    @Override
    public String path() {
        return "feed/reels_media/";
    }

    @Override
    public Class<FeedStoriesMediaResponse> getResponseType() {
        return FeedStoriesMediaResponse.class;
    }

}
