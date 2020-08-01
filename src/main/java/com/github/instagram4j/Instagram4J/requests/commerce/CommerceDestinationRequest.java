package com.github.instagram4j.Instagram4J.requests.commerce;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.requests.PaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.commerce.CommerceDestinationResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CommerceDestinationRequest extends IGGetRequest<CommerceDestinationResponse> implements PaginatedRequest<CommerceDestinationResponse> {
    @Setter
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
    public Class<CommerceDestinationResponse> getResponseType() {
        return CommerceDestinationResponse.class;
    }
    
}
