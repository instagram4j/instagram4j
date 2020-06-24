package com.github.instagram4j.Instagram4J.responses.accounts;

import com.github.instagram4j.Instagram4J.models.IGLoggedInUser;
import com.github.instagram4j.Instagram4J.models.IGTwoFactorInfo;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.responses.challenge.IGChallenge;

import lombok.Data;

@Data
public class IGLoginResponse extends IGResponse {
    private IGLoggedInUser logged_in_user;
    private IGChallenge challenge;
    private IGTwoFactorInfo two_factor_info;
}