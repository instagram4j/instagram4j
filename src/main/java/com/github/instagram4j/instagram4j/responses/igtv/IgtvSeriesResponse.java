package com.github.instagram4j.instagram4j.responses.igtv;

import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class IgtvSeriesResponse extends IGResponse {
    private String id;
    private String title;
    private String description;
}
