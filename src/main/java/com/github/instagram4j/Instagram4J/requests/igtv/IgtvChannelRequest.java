package com.github.instagram4j.Instagram4J.requests.igtv;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPaginatedRequest;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.igtv.IgtvChannelResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class IgtvChannelRequest extends IGPostRequest<IgtvChannelResponse> implements IGPaginatedRequest<IgtvChannelResponse> {
    @NonNull
    private String _id;
    @Setter
    private String max_id;
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IgtvChannelPayload();
    }

    @Override
    public String path() {
        return "igtv/channel/";
    }

    @Override
    public Class<IgtvChannelResponse> getResponseType() {
        return IgtvChannelResponse.class;
    }
    
    @Data
    @JsonInclude(Include.NON_NULL)
    public class IgtvChannelPayload extends IGPayload {
        private String id = _id;
        @JsonProperty("max_id")
        private String _max_id = max_id;
    }

}
