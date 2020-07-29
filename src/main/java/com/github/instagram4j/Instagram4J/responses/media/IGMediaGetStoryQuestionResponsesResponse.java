package com.github.instagram4j.Instagram4J.responses.media;

import com.github.instagram4j.Instagram4J.models.media.reel.IGResponderInfo;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMediaGetStoryQuestionResponsesResponse extends IGResponse {
    private IGResponderInfo responder_info;
}
