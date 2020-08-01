package com.github.instagram4j.Instagram4J.requests.media;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.requests.IGPaginatedRequest;
import com.github.instagram4j.Instagram4J.responses.media.MediaGetStoryPollVotersResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
public class MediaGetStoryPollVotersRequest extends IGGetRequest<MediaGetStoryPollVotersResponse> implements IGPaginatedRequest<MediaGetStoryPollVotersResponse> {
    @NonNull
    private String reel_id, poll_id;
    @Setter
    private String max_id;

    @Override
    public String path() {
        return String.format("media/%s/%s/story_poll_voters/", reel_id, poll_id);
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("max_id", max_id);
    }

    @Override
    public Class<MediaGetStoryPollVotersResponse> getResponseType() {
        return MediaGetStoryPollVotersResponse.class;
    }

}
