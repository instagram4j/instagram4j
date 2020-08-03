package com.github.instagram4j.instagram4j.responses.live;

import java.util.List;

import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class LiveGetQuestionsResponse extends IGResponse {
    private List<LiveQuestions> questions;
    
    @Data
    public static class LiveQuestions {
        private String text;
        private long qid;
        private String source;
        private Profile user;
        private long timestamp;
    }
}
