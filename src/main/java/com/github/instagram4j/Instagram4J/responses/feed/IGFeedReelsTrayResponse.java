package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.feed.IGReel;
import com.github.instagram4j.Instagram4J.models.live.IGBroadcast;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFeedReelsTrayResponse extends IGResponse {
    private List<IGReel> tray;
    private List<IGBroadcast> broadcasts;
}
