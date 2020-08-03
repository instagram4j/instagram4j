package com.github.instagram4j.instagram4j.requests.music;

import com.github.instagram4j.instagram4j.requests.IGGetRequest;
import com.github.instagram4j.instagram4j.responses.music.MusicTrackLyricsResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MusicTrackLyricsRequest extends IGGetRequest<MusicTrackLyricsResponse> {
    @NonNull
    private String _track_id;
    
    @Override
    public String path() {
        return "music/track/" + _track_id + "/lyrics/";
    }

    @Override
    public Class<MusicTrackLyricsResponse> getResponseType() {
        return MusicTrackLyricsResponse.class;
    }
    
}
