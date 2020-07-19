package com.github.instagram4j.Instagram4J.requests.igtv;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.igtv.IGIgtvBrowseFeedResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class IGIgtvBrowseFeedRequest extends IGGetRequest<IGIgtvBrowseFeedResponse> {
    private String _max_id;

    @Override
    public String path() {
        return "igtv/browse_feed/";
    }
    
    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", _max_id);
    }

    @Override
    public Class<IGIgtvBrowseFeedResponse> getResponseType() {
        return IGIgtvBrowseFeedResponse.class;
    }
    
}
