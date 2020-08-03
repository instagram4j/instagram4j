package com.github.instagram4j.instagram4j.requests.discover;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.responses.discover.DiscoverTopicalExploreResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class DiscoverTopicalExploreRequest extends IGGetRequest<DiscoverTopicalExploreResponse> implements IGPaginatedRequest<DiscoverTopicalExploreResponse> {
    @Setter
    private String max_id = "0";
    private String _cluster_id;
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id,
                              "cluster_id", _cluster_id, 
                              "module", "explore_popular", 
                              "use_sectional_payload", "true", 
                              "omit_cover_media", "true", 
                              "session_id", client.getSessionId());
    }

    @Override
    public String path() {
        return "discover/topical_explore/";
    }

    @Override
    public Class<DiscoverTopicalExploreResponse> getResponseType() {
        return DiscoverTopicalExploreResponse.class;
    }
}
