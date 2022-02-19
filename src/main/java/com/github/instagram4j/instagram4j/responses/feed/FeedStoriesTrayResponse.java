package com.github.instagram4j.instagram4j.responses.feed;

import java.util.List;

import com.github.instagram4j.instagram4j.models.feed.Stories;
import com.github.instagram4j.instagram4j.models.live.Broadcast;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class FeedStoriesTrayResponse extends IGResponse {
    private List<Stories> tray;
    private List<Broadcast> broadcasts;
}
