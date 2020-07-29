package com.github.instagram4j.Instagram4J.models.media.reel;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.user.IGProfile;

import lombok.Data;

@Data
public class IGVoterInfo extends IGBaseModel {
    private Long poll_id;
    private List<IGVoter> voters;
    private String max_id;
    private boolean more_available;
    
    @Data
    public static class IGVoter {
        private IGProfile user;
        private int vote;
        private Long ts;
    }
}
