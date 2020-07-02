package com.github.instagram4j.Instagram4J.requests.direct;

import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

public class IGDirectCreateGroupThreadRequest extends IGPostRequest<IGResponse> {
    private String title;
    private String[] userIds;

    public IGDirectCreateGroupThreadRequest(String title, String... user_ids) {
        this.title = title;
        this.userIds = user_ids;
    }

    @Override
    protected IGPayload getPayload() {
        return new IGDirectCreateGroupThreadPayload();
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
    public class IGDirectCreateGroupThreadPayload extends IGPayload {
        private String[] recipient_users = userIds;
        private String thread_title = title;
    }

}
