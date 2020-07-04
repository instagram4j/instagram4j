package com.github.instagram4j.Instagram4J.requests.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaConfigureResponse.IGMediaConfigureToIgtvResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGMediaConfigureToIgtvRequest extends IGPostRequest<IGMediaConfigureToIgtvResponse> {
    @NonNull
    private String uploadId, _title, _caption;
    private boolean upload_to_feed;
    
    @Override
    protected IGPayload getPayload() {
        return new IGMediaConfigureToIgtvPayload();
    }

    @Override
    public String path() {
        return "media/configure_to_igtv/";
    }

    @Override
    public Class<IGMediaConfigureToIgtvResponse> getResponseType() {
        return IGMediaConfigureToIgtvResponse.class;
    }

    @Data
    @JsonInclude(Include.NON_NULL)
    public class IGMediaConfigureToIgtvPayload extends IGPayload {
        private String upload_id = uploadId;
        private String title = _title;
        private String caption = _caption;
        private String igtv_share_preview_to_feed = upload_to_feed ? "1" : null;
        private String source_type = "4";
        private String length = "68";
        private String retryContext = "{\"num_step_auto_retry\":0,\"num_reupload\":0,\"num_step_manual_retry\":0}";
    }

}
