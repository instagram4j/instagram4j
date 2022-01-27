package com.github.instagram4j.instagram4j.requests.direct;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import java.util.Locale;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DirectThreadsActionRequest extends IGPostRequest<IGResponse> {
    @NonNull
    private String thread_id;
    @NonNull
    private DirectThreadsAction action;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload();
    }

    @Override
    public String path() {
        return String.format("direct_v2/threads/%s/%s/", thread_id, action.name().toLowerCase(new Locale("en", "US")));
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    public static enum DirectThreadsAction {
        HIDE, MUTE, UNMUTE, LEAVE;
    }

}
