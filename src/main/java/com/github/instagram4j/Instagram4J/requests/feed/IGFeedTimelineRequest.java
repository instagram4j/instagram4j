package com.github.instagram4j.Instagram4J.requests.feed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.feed.IGFeedTimelineResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class IGFeedTimelineRequest extends IGPostRequest<IGFeedTimelineResponse> {
    private String maxId = "";

    @Override
    public IGPayload getPayload() {
        FeedTimelinePayload payload = new FeedTimelinePayload();
        if (!maxId.isEmpty()) {
            payload.setMax_id(maxId);
            payload.setReason("pagination");
        }
        return payload;
    }

    @Override
    public String path() {
        return "/feed/timeline/";
    }

    @Override
    public Class<IGFeedTimelineResponse> getResponseType() {
        return IGFeedTimelineResponse.class;
    }

    @Data
    @JsonInclude(Include.NON_NULL)
    public static class FeedTimelinePayload extends IGPayload {
        private String max_id;
        private String reason = "cold_start_fetch";
        private String is_pull_to_refresh = "0";
        private String is_prefetch = "0";
    }
}
