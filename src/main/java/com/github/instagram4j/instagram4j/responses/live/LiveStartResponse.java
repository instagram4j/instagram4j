package com.github.instagram4j.instagram4j.responses.live;

import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class LiveStartResponse extends IGResponse {
    private String media_id;
}
