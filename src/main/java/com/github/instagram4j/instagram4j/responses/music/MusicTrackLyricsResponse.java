package com.github.instagram4j.instagram4j.responses.music;

import com.github.instagram4j.instagram4j.models.music.MusicLyrics;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class MusicTrackLyricsResponse extends IGResponse {
    private MusicLyrics lyrics;
}
