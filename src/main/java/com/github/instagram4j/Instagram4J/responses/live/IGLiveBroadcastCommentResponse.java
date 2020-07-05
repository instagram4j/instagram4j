package com.github.instagram4j.Instagram4J.responses.live;

import com.github.instagram4j.Instagram4J.models.media.timeline.IGComment;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGLiveBroadcastCommentResponse extends IGResponse {
    private IGComment comment;
}
