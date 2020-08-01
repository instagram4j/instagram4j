package com.github.instagram4j.Instagram4J.responses.feed;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.Instagram4J.models.feed.Reel;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class FeedUserReelsMediaResponse extends IGResponse {
    @JsonUnwrapped
    private Reel reel;
}
