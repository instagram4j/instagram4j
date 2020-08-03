package com.github.instagram4j.instagram4j.responses.direct;

import com.github.instagram4j.instagram4j.models.direct.IGThread;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class DirectThreadsResponse extends IGResponse {
    private IGThread thread;
}
