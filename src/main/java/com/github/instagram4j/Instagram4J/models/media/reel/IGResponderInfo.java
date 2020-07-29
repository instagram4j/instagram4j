package com.github.instagram4j.Instagram4J.models.media.reel;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.user.IGProfile;

import lombok.Data;

@Data
public class IGResponderInfo extends IGBaseModel {
    private Long question_id;
    private String question;
    private String question_type;
    private List<IGResponder> responders;
    private String max_id;
    private boolean more_available;
    
    @Data
    public static class IGResponder {
        private IGProfile user;
        private String response;
        private String id;
        private Long ts;
    }
}
