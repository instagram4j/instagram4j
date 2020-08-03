package com.github.instagram4j.instagram4j.responses.media;

import com.github.instagram4j.instagram4j.models.media.reel.ResponderInfo;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class MediaGetStoryQuestionResponsesResponse extends IGPaginatedResponse {
    private ResponderInfo responder_info;

    @Override
    public String getNext_max_id() {
        return this.responder_info.getMax_id();
    }

    @Override
    public boolean isMore_available() {
        return this.responder_info.isMore_available();
    }
}
