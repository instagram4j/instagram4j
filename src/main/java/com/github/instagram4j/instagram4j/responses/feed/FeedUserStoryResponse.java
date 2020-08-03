package com.github.instagram4j.instagram4j.responses.feed;

import com.github.instagram4j.instagram4j.models.feed.Reel;
import com.github.instagram4j.instagram4j.models.live.Broadcast;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class FeedUserStoryResponse extends IGResponse {
    private Reel reel;
    private Broadcast broadcast;
}
