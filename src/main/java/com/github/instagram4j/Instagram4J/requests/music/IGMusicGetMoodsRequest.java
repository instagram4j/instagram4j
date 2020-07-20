package com.github.instagram4j.Instagram4J.requests.music;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.music.IGMusicGetResponse;

public class IGMusicGetMoodsRequest extends IGPostRequest<IGMusicGetResponse> {

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload();
    }

    @Override
    public String path() {
        return "music/moods/";
    }

    @Override
    public Class<IGMusicGetResponse> getResponseType() {
        return IGMusicGetResponse.class;
    }

}
