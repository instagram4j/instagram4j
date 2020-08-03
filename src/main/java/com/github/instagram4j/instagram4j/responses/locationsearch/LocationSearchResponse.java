package com.github.instagram4j.instagram4j.responses.locationsearch;

import java.util.List;

import com.github.instagram4j.instagram4j.models.location.Location.Venue;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class LocationSearchResponse extends IGResponse {
    private List<Venue> venues;
    private String request_id;
    private String rank_token;
}
