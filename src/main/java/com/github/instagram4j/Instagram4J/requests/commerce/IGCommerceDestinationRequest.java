package com.github.instagram4j.Instagram4J.requests.commerce;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.commerce.IGCommerceDestinationResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class IGCommerceDestinationRequest extends IGGetRequest<IGCommerceDestinationResponse> {
    private String max_id = "0";
    
    @Override
    public String path() {
        return "commerce/destination/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id, "cluster_id", "shopping");
    }

    @Override
    public Class<IGCommerceDestinationResponse> getResponseType() {
        return IGCommerceDestinationResponse.class;
    }
    
}
