package com.github.instagram4j.instagram4j.responses.feed;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.instagram4j.models.feed.Reel;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class FeedReelsMediaResponse extends IGResponse {
    @JsonUnwrapped
    private Map<String, Reel> reels = new HashMap<String, Reel>();
}
