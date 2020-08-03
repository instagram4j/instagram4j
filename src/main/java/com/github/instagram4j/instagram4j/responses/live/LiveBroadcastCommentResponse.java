package com.github.instagram4j.instagram4j.responses.live;

import com.github.instagram4j.instagram4j.models.media.timeline.Comment;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class LiveBroadcastCommentResponse extends IGResponse {
    private Comment comment;
}
