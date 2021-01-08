package com.github.instagram4j.instagram4j.requests.commerce;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.responses.commerce.CommerceDestinationResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CommerceDestinationRequest extends IGGetRequest<CommerceDestinationResponse>
        implements IGPaginatedRequest {
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
