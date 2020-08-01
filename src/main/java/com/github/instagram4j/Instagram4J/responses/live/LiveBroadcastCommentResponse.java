package com.github.instagram4j.Instagram4J.responses.live;

import com.github.instagram4j.Instagram4J.models.media.timeline.Comment;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class LiveBroadcastCommentResponse extends IGResponse {
    private Comment comment;
}
