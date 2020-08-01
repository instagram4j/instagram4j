package com.github.instagram4j.Instagram4J.responses.music;

import com.github.instagram4j.Instagram4J.models.music.MusicLyrics;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class MusicTrackLyricsResponse extends IGResponse {
    private MusicLyrics lyrics;
}
