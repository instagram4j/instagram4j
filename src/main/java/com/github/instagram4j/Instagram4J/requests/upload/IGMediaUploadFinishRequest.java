package com.github.instagram4j.Instagram4J.requests.upload;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGMediaUploadFinishRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String uploadId;

    @Override
    protected IGPayload getPayload() {
        return new IGMediaUploadFinishPayload();
    }

    @Override
    public String path() {
        return "media/upload_finish/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    @Data
    public class IGMediaUploadFinishPayload extends IGPayload {
        private String upload_id = uploadId;
    }
}