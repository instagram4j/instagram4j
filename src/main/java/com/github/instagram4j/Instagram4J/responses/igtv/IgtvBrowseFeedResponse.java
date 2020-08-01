package com.github.instagram4j.Instagram4J.responses.igtv;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.instagram4j.Instagram4J.models.igtv.Channel;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;

import lombok.Data;

@Data
public class IgtvBrowseFeedResponse extends IGPaginatedResponse {
    private Channel my_channel;
    private List<Channel> channels;
    @JsonProperty("max_id")
    private String next_max_id;
    private boolean more_available;
}
