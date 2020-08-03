package com.github.instagram4j.instagram4j.requests.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse.MediaConfigureToIgtvResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class MediaConfigureToIgtvRequest extends IGPostRequest<MediaConfigureToIgtvResponse> {
    @NonNull
    private String uploadId, _title, _caption;
    private boolean upload_to_feed;
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new MediaConfigureToIgtvPayload();
    }

    @Override
    public String path() {
        return "media/configure_to_igtv/";
    }

    @Override
    public Class<MediaConfigureToIgtvResponse> getResponseType() {
        return MediaConfigureToIgtvResponse.class;
    }

    @Data
    @JsonInclude(Include.NON_NULL)
    public class MediaConfigureToIgtvPayload extends IGPayload {
        private String upload_id = uploadId;
        private String title = _title;
        private String caption = _caption;
        private String igtv_share_preview_to_feed = upload_to_feed ? "1" : null;
        private String source_type = "4";
        private String length = "68";
        private String retryContext = "{\"num_step_auto_retry\":0,\"num_reupload\":0,\"num_step_manual_retry\":0}";
    }

}
