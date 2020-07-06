package com.github.instagram4j.Instagram4J.responses.live;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.user.IGProfile;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGLiveGetQuestionsResponse extends IGResponse {
    private List<IGLiveQuestions> questions;
    
    @Data
    public static class IGLiveQuestions {
        private String text;
        private long qid;
        private String source;
        private IGProfile user;
        private long timestamp;
    }
}
