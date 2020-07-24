package com.github.instagram4j.Instagram4J.requests.feed;

import java.util.stream.Stream;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Getter;

public class IGFeedReelsMediaRequest extends IGPostRequest<IGResponse> {
    private String[] _ids;
    
    public IGFeedReelsMediaRequest(Object... ids) {
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
