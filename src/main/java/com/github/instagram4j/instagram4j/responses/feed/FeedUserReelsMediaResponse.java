package com.github.instagram4j.instagram4j.responses.feed;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.instagram4j.models.feed.Reel;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class FeedUserReelsMediaResponse extends IGResponse {
    @JsonUnwrapped
    private Reel reel;
}
