package com.github.instagram4j.Instagram4J.requests.igtv;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.igtv.IGIgtvSeriesResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGIgtvSeriesCreateRequest extends IGPostRequest<IGIgtvSeriesResponse> {
    @NonNull
    private String _title, _description;
    
    @Override
    protected IGPayload getPayload() {
        return new IGIgtvSeriesCreatePayload();
    }

    @Override
    public String path() {
        return "igtv/series/create/";
    }

    @Override
    public Class<IGIgtvSeriesResponse> getResponseType() {
        return IGIgtvSeriesResponse.class;
    }
    
    @Data
    public class IGIgtvSeriesCreatePayload extends IGPayload {
        private String title = _title;
        private String description = _description;
    }

}
