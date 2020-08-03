package com.github.instagram4j.instagram4j.requests.direct;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

public class DirectCreateGroupThreadRequest extends IGPostRequest<IGResponse> {
    private String title;
    private String[] userIds;

    public DirectCreateGroupThreadRequest(String title, String... user_ids) {
        this.title = title;
        this.userIds = user_ids;
    }

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new DirectCreateGroupThreadPayload();
    }

    @Override
    public String path() {
        return "direct_v2/create_group_thread/";
    }

    @Override
    public Class<IGResponse> getResponseType() {
        return IGResponse.class;
    }

    @Data
    public class DirectCreateGroupThreadPayload extends IGPayload {
        private String[] recipient_users = userIds;
        private String thread_title = title;
    }

}
