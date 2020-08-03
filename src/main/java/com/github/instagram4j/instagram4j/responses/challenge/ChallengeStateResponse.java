package com.github.instagram4j.instagram4j.responses.challenge;

import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ChallengeStateResponse extends IGResponse {
    private String step_name;
    private StepData step_data;

    @Getter
    @Setter
    public static class StepData {
        private String choice;
        private String email;
    }
}
