package com.github.instagram4j.Instagram4J.responses.feed;

import com.github.instagram4j.Instagram4J.models.feed.IGReel;
import com.github.instagram4j.Instagram4J.models.live.IGBroadcast;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFeedUserStoryResponse extends IGResponse {
    private IGReel reel;
    private IGBroadcast broadcast;
}
