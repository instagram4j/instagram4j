package com.github.instagram4j.Instagram4J.responses.live;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.media.timeline.IGComment;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGComment.IGCaption;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGLiveBroadcastGetCommentResponse extends IGResponse {
    private boolean comment_likes_enabled;
    private List<IGComment> comments;
    private int comment_count;
    private IGCaption caption;
    private boolean is_first_fetch;
}
