package com.github.instagram4j.instagram4j.responses.live;

import com.github.instagram4j.instagram4j.responses.IGResponse;

import java.util.List;

import lombok.Data;

@Data
public class LiveBroadcastThumbnailsResponse extends IGResponse {
    private List<String> thumbnails;
}
