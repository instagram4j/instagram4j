package com.github.instagram4j.Instagram4J.responses.direct;

import com.github.instagram4j.Instagram4J.models.direct.IGThread;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class DirectThreadsResponse extends IGResponse {
    private IGThread thread;
}
