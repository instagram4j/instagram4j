package com.github.instagram4j.Instagram4J.requests.music;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.music.IGMusicTrendingResponse;

import lombok.Getter;

public class IGMusicTrendingRequest extends IGPostRequest<IGMusicTrendingResponse> {

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private String session_id = client.getSessionId();
        };
    }

    @Override
    public String path() {
        return "music/trending/";
    }

    @Override
    public Class<IGMusicTrendingResponse> getResponseType() {
        return IGMusicTrendingResponse.class;
    }

}
