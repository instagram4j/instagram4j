package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGMediaOnlyMeRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _media_id;

    @Override
    protected IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            private String media_id = _media_id;
        };
    }

    @Override
    public String path() {
        return "media/" + _media_id + "/only_me/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
}
