package com.github.instagram4j.instagram4j.requests.highlights;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.highlights.HighlightsMediaResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HighlightsMediaRequest extends IGGetRequest<HighlightsMediaResponse> {
    @NonNull
    private String id;

    @Override
    public String path() {
        return "feed/reels_media/?user_ids=" + id;
    }

    @Override
    public Class<HighlightsMediaResponse> getResponseType() {
        return HighlightsMediaResponse.class;
    }
}
