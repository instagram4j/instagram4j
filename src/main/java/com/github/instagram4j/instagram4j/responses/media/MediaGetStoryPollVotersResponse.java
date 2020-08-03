package com.github.instagram4j.instagram4j.responses.media;

import com.github.instagram4j.instagram4j.models.media.reel.VoterInfo;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class MediaGetStoryPollVotersResponse extends IGPaginatedResponse {
    private VoterInfo voter_info;

    @Override
    public String getNext_max_id() {
        return voter_info.getMax_id();
    }

    @Override
    public boolean isMore_available() {
        return voter_info.isMore_available();
    }
    
}
