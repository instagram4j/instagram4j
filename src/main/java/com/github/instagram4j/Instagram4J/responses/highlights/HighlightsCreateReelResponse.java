package com.github.instagram4j.Instagram4J.responses.highlights;

import com.github.instagram4j.Instagram4J.models.feed.Reel;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class HighlightsCreateReelResponse extends IGResponse {
    private Reel reel;
}
