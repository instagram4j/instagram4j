package com.github.instagram4j.instagram4j.requests.highlights;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HighlightsDeleteStoryRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String _highlight_id;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload();
    }

    @Override
    public String path() {
        return "highlights/" + _highlight_id + "/delete_reel/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
