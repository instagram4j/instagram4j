package com.github.instagram4j.Instagram4J.requests.igtv;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.igtv.IGIgtvChannelResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGIgtvChannelRequest extends IGPostRequest<IGIgtvChannelResponse> {
    @NonNull
    private String _id;
    private String _max_id;
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGIgtvChannelPayload();
    }

    @Override
    public String path() {
        return "igtv/channel/";
    }

    @Override
    public Class<IGIgtvChannelResponse> getResponseType() {
        return IGIgtvChannelResponse.class;
    }
    
    @Data
    @JsonInclude(Include.NON_NULL)
    public class IGIgtvChannelPayload extends IGPayload {
        private String id = _id;
        private String max_id = _max_id;
    }

}
