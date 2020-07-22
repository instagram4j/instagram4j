package com.github.instagram4j.Instagram4J.requests.highlights;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.highlights.IGHighlightsUserTrayResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGHighlightsUserTrayRequest extends IGGetRequest<IGHighlightsUserTrayResponse> {
    @NonNull
    private Long pk;

    @Override
    public String path() {
        return "highlights/" + pk + "/highlights_tray/";
    }

    @Override
    public Class<IGHighlightsUserTrayResponse> getResponseType() {
        return IGHighlightsUserTrayResponse.class;
    }

}
