package com.github.instagram4j.instagram4j.requests.igtv;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.igtv.IgtvSeriesResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IgtvSeriesAddEpisodeRequest extends IGPostRequest<IgtvSeriesResponse> {
    @NonNull
    private String _series;
    @NonNull
    private Long pk;
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IgtvSeriesAddEpisodePayload();
    }

    @Override
    public String path() {
        return "igtv/series/" + _series + "/add_episode/";
    }

    @Override
    public Class<IgtvSeriesResponse> getResponseType() {
        return IgtvSeriesResponse.class;
    }
    
    @Data
    public class IgtvSeriesAddEpisodePayload extends IGPayload {
        private String media_id = pk.toString();
    }

}
