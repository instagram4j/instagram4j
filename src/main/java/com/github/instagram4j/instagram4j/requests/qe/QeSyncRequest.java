package com.github.instagram4j.instagram4j.requests.qe;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.IGConstants;
import com.github.instagram4j.instagram4j.models.IGBaseModel;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class QeSyncRequest extends IGPostRequest<IGResponse> {
    private boolean preLogin;
    
    @Override
    public String baseApiUrl() {
        return preLogin ? IGConstants.B_BASE_API_URL : super.baseApiUrl();
    }
    
    @Override
    protected IGBaseModel getPayload(IGClient client) {
        return preLogin ? new PrePayload(client.getGuid()) : new IGPayload() {
            @Getter
            private String experiments = IGConstants.DEVICE_EXPERIMENTS;
        };
    }

    @Override
    public String path() {
        return "qe/sync/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
    @Data
    private class PrePayload extends IGBaseModel {
        private final String id;
        private final String server_config_retrieval = "1";
        private final String experiments = IGConstants.DEVICE_EXPERIMENTS;
    }
    
    @Data
    private class PostPayload extends IGPayload {
        private final String experiments = IGConstants.DEVICE_EXPERIMENTS;
    }

}
