package com.github.instagram4j.instagram4j.requests.users;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.users.UserResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsersInfoRequest extends IGGetRequest<UserResponse> {
    private long userId;

    @Override
    public String path() {
        return "users/" + userId + "/info/?from_module=blended_search";
    }

    @Override
    public Class<UserResponse> getResponseType() {
        return UserResponse.class;
    }

}
