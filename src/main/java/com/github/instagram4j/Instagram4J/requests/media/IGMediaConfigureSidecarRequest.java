package com.github.instagram4j.Instagram4J.requests.media;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.media.IGMediaResponse.IGMediaConfigureSidecarResponse;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class IGMediaConfigureSidecarRequest extends IGPostRequest<IGMediaConfigureSidecarResponse> {
    @NonNull
    private List<AlbumChildrenMetadata> children;
    @NonNull
    private String _caption;

    @Override
    protected IGPayload getPayload() {
        return new IGPayload() {
            @Getter
            @Setter
            private List<?> children_metadata = children;
            @Getter
            @Setter
            private String caption = _caption;
            @Getter
            @Setter
            private String client_sidecar_id = String.valueOf(System.currentTimeMillis());
        };
    }

    @Override
    public String path() {
        return "media/configure_sidecar/";
    }

    @Override
    public Class<IGMediaConfigureSidecarResponse> getResponseType() {
        return IGMediaConfigureSidecarResponse.class;
    }

    @Data
    @Builder
    @JsonInclude(Include.NON_NULL)
    public static class AlbumChildrenMetadata {
        private String upload_id;
    }
}
