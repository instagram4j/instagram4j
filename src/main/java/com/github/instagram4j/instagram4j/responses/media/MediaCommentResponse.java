package com.github.instagram4j.instagram4j.responses.media;

import com.github.instagram4j.instagram4j.models.media.timeline.Comment;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class MediaCommentResponse extends IGResponse {
    private Comment comment;
}
