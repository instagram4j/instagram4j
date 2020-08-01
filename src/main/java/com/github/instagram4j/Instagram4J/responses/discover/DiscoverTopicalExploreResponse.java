package com.github.instagram4j.Instagram4J.responses.discover;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.discover.Cluster;
import com.github.instagram4j.Instagram4J.models.discover.SectionalItem;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class DiscoverTopicalExploreResponse extends IGPaginatedResponse {
    private List<SectionalItem> sectional_items;
    private String rank_token;
    private List<Cluster> clusters;
    private String next_max_id;
    private boolean more_available;
}
