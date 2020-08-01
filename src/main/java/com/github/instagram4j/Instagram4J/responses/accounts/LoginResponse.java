package com.github.instagram4j.Instagram4J.responses.accounts;

import com.github.instagram4j.Instagram4J.models.user.User;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.responses.challenge.Challenge;

import lombok.Data;

@Data
public class LoginResponse extends IGResponse {
    private User logged_in_user;
    private Challenge challenge;
    private TwoFactorInfo two_factor_info;

    @Data
    public class TwoFactorInfo {
        private String two_factor_identifier;
    }
}