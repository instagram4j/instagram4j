package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGLoginResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class IGLoginRequest extends IGPostRequest<IGLoginResponse> {
	@NonNull
	private String username;
	@NonNull
	private String password;

	@Override
	public String getUrl() {
		return "/accounts/login/";
	}

	@Override
	public IGPayload getPayload() {
		LoginPayload payload = LoginPayload.builder()
				.username(username)
				.password(password)
				.phone_id(IGUtils.randomUuid())
				.build();

		return payload;
	}
	
	@Override
	public Class<IGLoginResponse> getResponseType() {
		return IGLoginResponse.class;
	}

	@Getter
	@Setter
	@Builder
	public static class LoginPayload extends IGPayload {
		private String username;
		private String phone_id;
		private String password;
		@Builder.Default
		private int login_attempt_account = 0;
	}
	
}
