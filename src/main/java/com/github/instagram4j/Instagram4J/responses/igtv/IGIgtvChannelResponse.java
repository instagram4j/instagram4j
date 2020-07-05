package com.github.instagram4j.Instagram4J.responses.igtv;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.Instagram4J.models.igtv.IGChannel;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGIgtvChannelResponse extends IGResponse {
    @JsonUnwrapped
    private IGChannel channel;
}
