package com.github.instagram4j.Instagram4J.requests.users;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IGUsersInfoRequest extends IGGetRequest<IGResponse> {
    private long userId;
    
    @Override
    public String path() {
        return "/users/" + userId + "/info/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
