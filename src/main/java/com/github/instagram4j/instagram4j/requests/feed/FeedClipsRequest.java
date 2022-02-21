package com.github.instagram4j.instagram4j.requests.feed;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.IGBaseModel;
import com.github.instagram4j.instagram4j.models.IGPayload;
import com.github.instagram4j.instagram4j.models.clips.ServerDrivenCacheConfig;
import com.github.instagram4j.instagram4j.models.clips.SessionInfo;
import com.github.instagram4j.instagram4j.requests.IGPostRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedClipsResponse;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@AllArgsConstructor
public class FeedClipsRequest extends IGPostRequest<FeedClipsResponse> {
    @NonNull
    private FeedType feedType;
    private FeedClipsRequestPayload payload;

    @Override
    public String path() {
        if (feedType.equals(FeedType.DISCOVER))
            return "clips/discover/";
        else
            return "clips/connected/";
    }

    @Override
    public Class<FeedClipsResponse> getResponseType() {
        return FeedClipsResponse.class;
    }

    @Override
    protected IGBaseModel getPayload(IGClient client) {
        SessionInfo session_info = new SessionInfo();
        session_info.setSession_id(client.getSessionId());
        session_info.setMedia_info(Collections.emptyList());
        return new FeedClipsRequestPayload().session_info(session_info);
    }

    public enum FeedType{
        FOLLOWING, DISCOVER
    }

    @Data
    @Accessors(fluent = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Setter
    public static class FeedClipsRequestPayload extends IGPayload {
        private List<String> seen_reels = new ArrayList<>();
        private String max_id = "";
        private SessionInfo session_info;
        private ServerDrivenCacheConfig server_driven_cache_config = new ServerDrivenCacheConfig();
        private String container_module = "clips_viewer_clips_tab";
    }
}
