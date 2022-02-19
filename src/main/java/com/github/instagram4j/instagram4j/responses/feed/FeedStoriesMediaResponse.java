package com.github.instagram4j.instagram4j.responses.feed;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.instagram4j.models.feed.Stories;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class FeedStoriesMediaResponse extends IGResponse {
    @JsonUnwrapped
    private Map<String, Stories> stories = new HashMap<>();
}
