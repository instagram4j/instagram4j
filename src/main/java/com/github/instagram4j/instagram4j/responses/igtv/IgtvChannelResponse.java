package com.github.instagram4j.instagram4j.responses.igtv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.instagram4j.instagram4j.models.igtv.Channel;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

@Data
public class IgtvChannelResponse extends IGResponse implements IGPaginatedResponse {
    @JsonUnwrapped
    private Channel channel;
    @JsonProperty("max_id")
    private String next_max_id;
    private boolean more_available;
}
