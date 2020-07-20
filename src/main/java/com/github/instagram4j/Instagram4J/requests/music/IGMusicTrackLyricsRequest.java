package com.github.instagram4j.Instagram4J.requests.music;

import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.music.IGMusicTrackLyricsResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGMusicTrackLyricsRequest extends IGGetRequest<IGMusicTrackLyricsResponse> {
    @NonNull
    private String _track_id;
    
    @Override
    public String path() {
        return "music/track/" + _track_id + "/lyrics/";
    }

    @Override
    public Class<IGMusicTrackLyricsResponse> getResponseType() {
        return IGMusicTrackLyricsResponse.class;
    }
    
}
