package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGDirectThreadsMarkItemSeenRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String thread_id;
    @NonNull
    private String item_id;

    @Override
    protected IGPayload getPayload() {
        return new IGDirectThreadsMarkItemSeenPayload();
    }

    @Override
    public String path() {
        return String.format("direct_v2/threads/%s/items/%s/seen/", thread_id, item_id);
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }
    
    @Data
    public class IGDirectThreadsMarkItemSeenPayload extends IGPayload {
        private String thread_id;
        private String item_id;
        private final String action = "mark_seen";
        private boolean use_unified_inbox = true;
    }

}
