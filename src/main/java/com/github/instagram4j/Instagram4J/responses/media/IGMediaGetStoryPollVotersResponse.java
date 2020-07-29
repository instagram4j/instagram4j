package com.github.instagram4j.Instagram4J.responses.media;

import com.github.instagram4j.Instagram4J.models.media.reel.IGVoterInfo;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMediaGetStoryPollVotersResponse extends IGResponse {
    private IGVoterInfo voter_info;
}
