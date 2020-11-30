package com.github.instagram4j.instagram4j.requests.fbsearch;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.fbsearch.FbsearchPlacesResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class FbsearchPlacesRequest extends IGGetRequest<FbsearchPlacesResponse> {
    @NonNull
    private String query;
    @NonNull
    private Double lat, lon;
    private String page_token, rank_token;
    private final int count = 30;

    @Override
    public String path() {
        return "fbsearch/places";
    }

    @Override
    public String getQueryString(IGClient client) {

        return mapQueryString(
                "search_surface", "places_search_page",
                "timezone_offset", "0",
                "query", query,
                "lat", lat.toString(),
                "lng", lon.toString(),
                "count", String.valueOf(count),
                "rank_token", rank_token,
                "page_token", page_token);
    }

    @Override
    public Class<FbsearchPlacesResponse> getResponseType() {
        return FbsearchPlacesResponse.class;
    }

}
