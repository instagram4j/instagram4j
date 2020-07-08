package com.github.instagram4j.Instagram4J.models.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
public class IGLocation extends IGBaseModel {
    private String name;
    private String external_id_source;
    private double lat;
    private double lng;
    private String address;
    private int minimum_age;
    
    @Data
    public static class IGVenue extends IGLocation {
        @JsonProperty("external_id")
        private String id;
    }
}
