package com.github.instagram4j.Instagram4J.responses.highlights;

import com.github.instagram4j.Instagram4J.models.feed.IGReel;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGHighlightsCreateReelResponse extends IGResponse {
    private IGReel reel;
}
