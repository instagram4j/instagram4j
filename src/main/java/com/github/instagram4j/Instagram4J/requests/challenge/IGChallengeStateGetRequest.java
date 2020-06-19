package com.github.instagram4j.Instagram4J.requests.challenge;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGChallengeStateResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGChallengeStateGetRequest extends IGGetRequest<IGChallengeStateResponse> {
	@NonNull
	private String path;
	
	@Override
	public String path() {
		return path;
	}
	
	@Override
	public String getQueryStrings() {
		return this.mapQueryString("guid", client.getGuid(), "device_id", client.getDeviceId());
	}

	@Override
	public Class<IGChallengeStateResponse> getResponseType() {
		return IGChallengeStateResponse.class;
	}

}
