package com.github.instagram4j.Instagram4J.responses.feed;

import com.github.instagram4j.Instagram4J.models.feed.Reel;
import com.github.instagram4j.Instagram4J.models.live.Broadcast;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class FeedUserStoryResponse extends IGResponse {
    private Reel reel;
    private Broadcast broadcast;
}
