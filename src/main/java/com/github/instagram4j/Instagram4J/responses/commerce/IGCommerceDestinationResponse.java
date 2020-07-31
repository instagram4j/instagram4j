package com.github.instagram4j.Instagram4J.responses.commerce;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.discover.IGSectionalMediaGridItem;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class IGCommerceDestinationResponse extends IGPaginatedResponse {
    private List<IGSectionalMediaGridItem> sectional_items;
    private String rank_token;
    private String next_max_id;
    private boolean more_available;
}
