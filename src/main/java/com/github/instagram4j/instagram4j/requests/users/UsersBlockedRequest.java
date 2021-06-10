package com.github.instagram4j.instagram4j.requests.users;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.users.UsersBlockedResponse;

public class UsersBlockedRequest extends IGGetRequest<UsersBlockedResponse> {

	@Override
    public String path() {
        return "users/blocked_list/";
    }

	@Override
	public Class<UsersBlockedResponse> getResponseType() {
		return UsersBlockedResponse.class;
	}

}
