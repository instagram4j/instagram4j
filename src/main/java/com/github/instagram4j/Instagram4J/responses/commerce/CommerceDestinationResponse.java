package com.github.instagram4j.Instagram4J.responses.commerce;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.discover.SectionalMediaGridItem;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class CommerceDestinationResponse extends IGPaginatedResponse {
    private List<SectionalMediaGridItem> sectional_items;
    private String rank_token;
    private String next_max_id;
    private boolean more_available;
}
