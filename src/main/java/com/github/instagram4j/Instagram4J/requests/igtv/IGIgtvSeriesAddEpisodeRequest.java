package com.github.instagram4j.Instagram4J.requests.igtv;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.igtv.IGIgtvSeriesResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGIgtvSeriesAddEpisodeRequest extends IGPostRequest<IGIgtvSeriesResponse> {
    @NonNull
    private String _series;
    @NonNull
    private Long pk;
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGIgtvSeriesAddEpisodePayload();
    }

    @Override
    public String path() {
        return "igtv/series/" + _series + "/add_episode/";
    }

    @Override
    public Class<IGIgtvSeriesResponse> getResponseType() {
        return IGIgtvSeriesResponse.class;
    }
    
    @Data
    public class IGIgtvSeriesAddEpisodePayload extends IGPayload {
        private String media_id = pk.toString();
    }

}
