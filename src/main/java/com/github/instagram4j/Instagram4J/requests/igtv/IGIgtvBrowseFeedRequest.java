package com.github.instagram4j.Instagram4J.requests.igtv;

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
        return String.format("igtv/%s/", _max_id != null ? "browse_feed" : "non_prefetch_browse_feed");
    }
    
    @Override
    public String getQueryString() {
        return _max_id != null ? mapQueryString("max_id", _max_id) : mapQueryString("prefetch", "1");
    }

    @Override
    public Class<IGIgtvBrowseFeedResponse> getResponseType() {
        return IGIgtvBrowseFeedResponse.class;
    }
    
}
