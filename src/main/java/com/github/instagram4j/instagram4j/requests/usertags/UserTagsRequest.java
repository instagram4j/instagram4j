package com.github.instagram4j.instagram4j.requests.usertags;


import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.usertags.UserTagsResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserTagsRequest extends IGGetRequest<UserTagsResponse> {
    private long userId;

    @Override
    public String path() {
        return "usertags/"+userId+"/feed/";
    }

    @Override
    public Class<UserTagsResponse> getResponseType() {
        return UserTagsResponse.class;
    }

}
