package com.github.instagram4j.Instagram4J.responses.live;

import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGLiveBroadcastLikeResponse extends IGResponse {
    private String likes;
    private String burst_likes;
}
