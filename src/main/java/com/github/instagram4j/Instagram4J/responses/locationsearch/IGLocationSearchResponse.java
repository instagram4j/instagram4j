package com.github.instagram4j.Instagram4J.responses.locationsearch;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.location.IGLocation.IGVenue;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGLocationSearchResponse extends IGResponse {
    private List<IGVenue> venues;
    private String request_id;
    private String rank_token;
}
