package com.github.instagram4j.Instagram4J.requests.locationsearch;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.locationsearch.LocationSearchResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LocationSearchRequest extends IGGetRequest<LocationSearchResponse> {
    @NonNull
    private Double longitude, latitude;
    @NonNull
    private String query;

    @Override
    public String path() {
        return "location_search/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("longitude", longitude.toString(), "latitude", latitude.toString(), "search_query", query);
    }

    @Override
    public Class<LocationSearchResponse> getResponseType() {
        return LocationSearchResponse.class;
    }

}
