package com.github.instagram4j.instagram4j.responses.feed;

import com.github.instagram4j.instagram4j.models.feed.Stories;
import com.github.instagram4j.instagram4j.models.live.Broadcast;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class FeedUserStoryResponse extends IGResponse {
    private Stories stories;
    private Broadcast broadcast;
}
