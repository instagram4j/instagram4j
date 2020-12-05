package com.github.instagram4j.instagram4j.responses.live;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.instagram4j.instagram4j.models.media.timeline.Comment;
import com.github.instagram4j.instagram4j.models.media.timeline.Comment.Caption;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

@Data
public class LiveBroadcastGetCommentResponse extends IGResponse {
    private boolean comment_likes_enabled;
    private List<Comment> comments;
    private int comment_count;
    private Caption caption;
    @JsonProperty("is_first_fetch")
    private boolean is_first_fetch;
}
