package com.github.instagram4j.instagram4j.responses.feed;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.instagram4j.models.feed.Stories;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class FeedUserStoriesMediaResponse extends IGResponse {
    @JsonUnwrapped
    private Stories stories;
}
