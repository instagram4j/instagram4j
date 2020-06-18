package com.github.instagram4j.Instagram4J.responses;

import com.github.instagram4j.Instagram4J.models.IGTwoFactorInfo;
import com.github.instagram4j.Instagram4J.models.IGUser;

import lombok.Data;

@Data
public class IGLoginResponse extends IGResponse {
	private IGUser logged_in_user;
	private IGChallenge challenge;
	private IGTwoFactorInfo two_factor_info;
}