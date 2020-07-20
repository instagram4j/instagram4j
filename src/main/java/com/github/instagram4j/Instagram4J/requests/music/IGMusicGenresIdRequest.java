package com.github.instagram4j.Instagram4J.requests.music;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.requests.music.IGMusicSearchRequest.IGMusicQueryPayload;
import com.github.instagram4j.Instagram4J.responses.music.IGMusicTrackResponse;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class IGMusicGenresIdRequest extends IGPostRequest<IGMusicTrackResponse> {
    @NonNull
    private String _id;
    private String _cursor = "0";
    
    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGMusicQueryPayload(client.getSessionId(), _cursor);
    }

    @Override
    public String path() {
        return "music/genres/" + _id + "/";
    }

    @Override
    public Class<IGMusicTrackResponse> getResponseType() {
        return IGMusicTrackResponse.class;
    }
    
}
