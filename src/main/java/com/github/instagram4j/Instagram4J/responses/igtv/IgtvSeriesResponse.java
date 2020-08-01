package com.github.instagram4j.Instagram4J.responses.igtv;

import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IgtvSeriesResponse extends IGResponse {
    private String id;
    private String title;
    private String description;
}
