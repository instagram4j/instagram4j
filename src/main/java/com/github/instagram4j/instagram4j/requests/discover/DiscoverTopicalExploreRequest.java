package com.github.instagram4j.instagram4j.requests.discover;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.responses.discover.DiscoverTopicalExploreResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
public class DiscoverTopicalExploreRequest extends IGGetRequest<DiscoverTopicalExploreResponse>
        implements IGPaginatedRequest<DiscoverTopicalExploreResponse> {
    @Setter
    private String max_id = "0";
    private String _cluster_id = "explore_all:0";
    @Setter
    @Accessors(chain = true, fluent = true)
    private Boolean is_prefetch;

    public DiscoverTopicalExploreRequest(String max_id, String cluster_id) {
        this.max_id = max_id;
        this._cluster_id = cluster_id;
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id,
                "is_prefetch", is_prefetch,
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
