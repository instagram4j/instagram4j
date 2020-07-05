package com.github.instagram4j.Instagram4J.responses.igtv;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.igtv.IGChannel;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGIgtvBrowseFeedResponse extends IGResponse {
    private IGChannel my_channel;
    private List<IGChannel> channels;
}
