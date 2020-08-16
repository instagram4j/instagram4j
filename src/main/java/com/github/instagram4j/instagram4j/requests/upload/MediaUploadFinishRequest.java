package com.github.instagram4j.instagram4j.requests.upload;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MediaUploadFinishRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String uploadId;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new MediaUploadFinishPayload();
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
    public class MediaUploadFinishPayload extends IGPayload {
        private String upload_id = uploadId;
    }
}
