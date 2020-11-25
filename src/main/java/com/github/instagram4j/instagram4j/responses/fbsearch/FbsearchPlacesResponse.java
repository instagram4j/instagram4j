package com.github.instagram4j.instagram4j.responses.fbsearch;

import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

import java.util.List;

@Data
public class FbsearchPlacesResponse extends IGResponse {

    private int num_results;
    private String rank_token;
    private String page_token;
    private String status;
    private boolean has_more;
    private List<SearchLocationLocation> items;

    @Data
    public static class SearchLocationLocation {
        private String title;
        private String subtitle;
        private FbsearchLocation fbsearchLocation;
    }

    @Data
    public static class FbsearchLocation {
        private long pk;
        private long facebook_places_id;
        private String short_name;
        private String external_source;
        private String name;
        private String address;
        private String city;
        private double lng;
        private double lat;
    }
}
