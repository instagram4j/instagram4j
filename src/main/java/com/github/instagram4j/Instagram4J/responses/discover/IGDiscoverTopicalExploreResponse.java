package com.github.instagram4j.Instagram4J.responses.discover;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.discover.IGCluster;
import com.github.instagram4j.Instagram4J.models.discover.IGSectionalItem;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGDiscoverTopicalExploreResponse extends IGResponse {
    private List<IGSectionalItem> sectional_items;
    private String rank_token;
    private List<IGCluster> clusters;
}
