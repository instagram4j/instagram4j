package com.github.instagram4j.Instagram4J.responses.discover;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.discover.IGCluster;
import com.github.instagram4j.Instagram4J.models.discover.IGSectionalItem;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class IGDiscoverTopicalExploreResponse extends IGPaginatedResponse {
    private List<IGSectionalItem> sectional_items;
    private String rank_token;
    private List<IGCluster> clusters;
    private String next_max_id;
    private boolean more_available;
}
