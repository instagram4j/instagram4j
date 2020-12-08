package com.github.instagram4j.instagram4j.requests.media;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MediaCommentUnlikeRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _comment_id;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private String comment_id = _comment_id;
        };
    }

    @Override
    public String path() {
        return String.format("media/%s/comment_unlike/", _comment_id);
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}