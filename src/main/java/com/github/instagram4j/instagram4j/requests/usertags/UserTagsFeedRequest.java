package com.github.instagram4j.instagram4j.requests.usertags;


import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.responses.usertags.UserTagsFeedResponse;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class UserTagsFeedRequest extends IGGetRequest<UserTagsFeedResponse> 
					implements IGPaginatedRequest {
	
    private long userId;
    
    @Setter 
    private String max_id;
    
    @Override
    public String path() {
        return "usertags/"+userId+"/feed/";
    }

    @Override
    public Class<UserTagsFeedResponse> getResponseType() {
        return UserTagsFeedResponse.class;
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }
}
