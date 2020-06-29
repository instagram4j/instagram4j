package com.github.instagram4j.Instagram4J.responses.direct;

import com.github.instagram4j.Instagram4J.models.direct.IGThread;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGDirectThreadsResponse extends IGResponse {
    private IGThread thread;
}
