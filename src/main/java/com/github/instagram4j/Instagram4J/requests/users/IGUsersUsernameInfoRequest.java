package com.github.instagram4j.Instagram4J.requests.users;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.users.IGUserResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGUsersUsernameInfoRequest extends IGGetRequest<IGUserResponse> {
    @NonNull
    private String username;

    @Override
    public String path() {
        return "users/" + username + "/usernameinfo/";
    }

    @Override
    public Class<IGUserResponse> getResponseType() {
        return IGUserResponse.class;
    }

}
