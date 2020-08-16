package com.github.instagram4j.instagram4j.requests.feed;

import java.util.stream.Stream;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Getter;

public class FeedReelsMediaRequest extends IGPostRequest<IGResponse> {
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
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
