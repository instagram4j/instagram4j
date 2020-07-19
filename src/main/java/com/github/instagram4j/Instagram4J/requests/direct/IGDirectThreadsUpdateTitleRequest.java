package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.direct.IGDirectThreadsResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGDirectThreadsUpdateTitleRequest extends IGPostRequest<IGDirectThreadsResponse> {
    @NonNull
    private String thread_id;
    @NonNull
    private String _title;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private String title = _title;
        };
    }

    @Override
    public String path() {
        return "direct_v2/threads/" + thread_id + "/update_title/";
    }

    @Override
    public Class<IGDirectThreadsResponse> getResponseType() {
        return IGDirectThreadsResponse.class;
    }

}
