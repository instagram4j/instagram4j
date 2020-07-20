package com.github.instagram4j.Instagram4J.requests.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGMediaCommentRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String id, _comment_text;
    private String _replied_to_comment_id;
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGMediaCommentPayload();
    }

    @Override
    public String path() {
        return "media/" + id + "/comment/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
    @Data
    @JsonInclude(Include.NON_NULL)
    private class IGMediaCommentPayload extends IGPayload {
        private String comment_text = _comment_text;
        private String replied_to_comment_id = _replied_to_comment_id;
    }

}
