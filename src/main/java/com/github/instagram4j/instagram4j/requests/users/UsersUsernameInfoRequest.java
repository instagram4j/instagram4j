package com.github.instagram4j.instagram4j.requests.users;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.users.UserResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsersUsernameInfoRequest extends IGGetRequest<UserResponse> {
    @NonNull
    private String username;

    @Override
    public String path() {
        return "users/" + username + "/usernameinfo/";
    }

    @Override
    public Class<UserResponse> getResponseType() {
        return UserResponse.class;
    }

}
