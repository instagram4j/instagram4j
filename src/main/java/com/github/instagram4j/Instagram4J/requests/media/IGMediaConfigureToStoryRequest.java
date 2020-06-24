package com.github.instagram4j.Instagram4J.requests.media;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.models.reelmedia.IGReelMetadataItem;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaConfigureResponse.IGMediaConfigureToStoryResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class IGMediaConfigureToStoryRequest extends IGPostRequest<IGMediaConfigureToStoryResponse> {
    @NonNull
    private String uploadId;
    private List<IGReelMetadataItem> story_metadata;
    private List<String> threadIds;

    public IGMediaConfigureToStoryRequest(String upload_id, List<IGReelMetadataItem> story_metadata) {
        this.uploadId = upload_id;
        this.story_metadata = story_metadata;
    }

    @Override
    protected IGPayload getPayload() {
        return constructPayload();
    }

    @Override
    public String path() {
        return "/media/configure_to_story/";
    }

    @Override
    public Class<IGMediaConfigureToStoryResponse> getResponseType() {
        return IGMediaConfigureToStoryResponse.class;
    }

    private IGMediaConfigureToStoryPayload constructPayload() {
        IGMediaConfigureToStoryPayload payload = new IGMediaConfigureToStoryPayload();
        if (story_metadata != null)
            story_metadata.forEach(data -> payload.addExtraProperty(data.key(), Arrays.asList(data)));
        return payload;
    }

    @Data
    @JsonInclude(Include.NON_NULL)
    public class IGMediaConfigureToStoryPayload extends IGPayload {
        private String upload_id = uploadId;
        private String source_type = "3";
        private String configure_mode = threadIds == null ? "1" : "2";
        private List<String> thread_ids = threadIds;
    }

}
