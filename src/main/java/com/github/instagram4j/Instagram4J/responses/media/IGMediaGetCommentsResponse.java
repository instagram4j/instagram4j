package com.github.instagram4j.Instagram4J.responses.media;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.media.timeline.IGComment;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGComment.IGCaption;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMediaGetCommentsResponse extends IGResponse {
    private List<IGComment> comments;
    private IGCaption caption;
    private String next_max_id;
}
