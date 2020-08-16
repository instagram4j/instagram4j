package com.github.instagram4j.instagram4j.models.media.reel;

import java.util.List;

import com.github.instagram4j.instagram4j.models.IGBaseModel;
import com.github.instagram4j.instagram4j.models.user.Profile;

import lombok.Data;

@Data
public class VoterInfo extends IGBaseModel {
    private Long poll_id;
    private List<Voter> voters;
    private String max_id;
    private boolean more_available;

    @Data
    public static class Voter {
        private Profile user;
        private int vote;
        private Long ts;
    }
}
