package com.github.instagram4j.instagram4j.responses.highlights;

import com.github.instagram4j.instagram4j.models.feed.Reel;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class HighlightsCreateReelResponse extends IGResponse {
    private Reel reel;
}
