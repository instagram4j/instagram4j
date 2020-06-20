package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.requests.accounts.IGLoginRequest.LoginPayload;
import com.github.instagram4j.Instagram4J.responses.accounts.IGLoginResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGTwoFactorLoginRequest extends IGPostRequest<IGLoginResponse> {
	@NonNull
	private String username, password, code, identifier;

	@Override
	public IGPayload getPayload() {
		return new LoginPayload(username, password, 0) {
			@Getter
			private final String verification_code = code;
			@Getter
			private final String two_factor_identifier = identifier;
		};
	}

	@Override
	public String path() {
		return "/accounts/two_factor_login/";
	}

	@Override
	public Class<IGLoginResponse> getResponseType() {
		return IGLoginResponse.class;
	}

}
