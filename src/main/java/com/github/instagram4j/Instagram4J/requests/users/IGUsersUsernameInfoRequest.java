package com.github.instagram4j.Instagram4J.requests.users;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

public class IGUsersUsernameInfoRequest extends IGGetRequest<IGResponse> {
    private String username;
    
    @Override
    public String path() {
        return "/users/" + username + "/usernameinfo/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

}
