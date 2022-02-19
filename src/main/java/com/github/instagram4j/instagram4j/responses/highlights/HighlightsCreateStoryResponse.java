package com.github.instagram4j.instagram4j.responses.highlights;

import com.github.instagram4j.instagram4j.models.feed.Stories;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class HighlightsCreateStoryResponse extends IGResponse {
    private Stories stories;
}
