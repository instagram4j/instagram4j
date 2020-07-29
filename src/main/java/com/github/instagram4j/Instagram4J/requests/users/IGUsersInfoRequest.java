package com.github.instagram4j.Instagram4J.requests.users;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.users.IGUserResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IGUsersInfoRequest extends IGGetRequest<IGUserResponse> {
    private long userId;

    @Override
    public String path() {
        return "users/" + userId + "/info/";
    }

    @Override
    public Class<IGUserResponse> getResponseType() {
        return IGUserResponse.class;
    }

}
