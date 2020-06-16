package com.github.instagram4j.Instagram4J.requests;

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
		return "accounts/login/";
	}

	@Override
	public String getPayload() {
		LoginPayload payload = LoginPayload.builder()
				.username(username)
				.password(password)
				._csrftoken("missing")
				.guid(client.getUuid())
				.device_id(client.getDeviceId())
				.phone_id(IGUtils.randomUuid())
				.build();

		return IGUtils.objectToJson(payload);
	}
	
	@Override
	public Class<IGLoginResponse> getResponseType() {
		return IGLoginResponse.class;
	}

	@Getter
	@Setter
	@Builder
	private static class LoginPayload {
		private String username;
		private String phone_id;
		private String _csrftoken;
		private String guid;
		private String device_id;
		private String password;
		@Builder.Default
		private int login_attempt_account = 0;
	}
	
}
