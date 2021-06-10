package com.github.instagram4j.instagram4j.requests.users;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.users.UserBlockedResponse;

public class UserBlockedRequest extends IGGetRequest<UserBlockedResponse> {

	@Override
    public String path() {
        return "users/blocked_list/";
    }

	@Override
	public Class<UserBlockedResponse> getResponseType() {
		return UserBlockedResponse.class;
	}

}
