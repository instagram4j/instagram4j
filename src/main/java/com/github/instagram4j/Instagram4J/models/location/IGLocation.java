package com.github.instagram4j.Instagram4J.models.location;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class IGLocation extends IGBaseModel {
    private Long pk;
    private String external_id;
    private String name;
    private String external_source;
    private Double lat;
    private Double lng;
    private String address;
    private Integer minimum_age;
    
    @Data
    public static class IGVenue extends IGLocation {
        @JsonAlias("external_id_source")
        private String external_source;
    }
}
