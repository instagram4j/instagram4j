package com.github.instagram4j.Instagram4J.responses.live;

import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGLiveBroadcastHeartbeatResponse extends IGResponse {
    private int viewer_count;
    private String broadcast_status;
    private String[] cobroadcaster_ids;
    private int offset_to_video_start;
}
