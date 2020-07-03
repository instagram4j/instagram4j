package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGMediaConfigureToIgtvRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String uploadId, _title, _caption;

    @Override
    protected IGPayload getPayload() {
        return new IGMediaConfigureToIgtvPayload();
    }

    @Override
    public String path() {
        return "media/configure_to_igtv/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    @Data
    public class IGMediaConfigureToIgtvPayload extends IGPayload {
        private String upload_id = uploadId;
        private String title = _title;
        private String caption = _caption;
        private String igtv_share_preview_to_feed;
        private String source_type = "4";
        private String length = "68";
        private String retryContext = "{\"num_step_auto_retry\":0,\"num_reupload\":0,\"num_step_manual_retry\":0}";
    }

}
