package com.github.instagram4j.instagram4j.requests.igtv;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.igtv.IgtvSearchResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class IgtvSearchRequest extends IGPostRequest<IgtvSearchResponse> {
    private String _query;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IgtvSearchPayload();
    }

    @Override
    public String path() {
        return String.format("igtv/%s/", _query != null ? "search" : "suggested_searches");
    }

    @Override
    public Class<IgtvSearchResponse> getResponseType() {
        return IgtvSearchResponse.class;
    }

    @Data
    @JsonInclude(Include.NON_NULL)
    public class IgtvSearchPayload extends IGPayload {
        private String query = _query;
    }

}
