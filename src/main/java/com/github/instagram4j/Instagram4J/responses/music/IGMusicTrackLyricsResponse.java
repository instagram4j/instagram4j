package com.github.instagram4j.Instagram4J.responses.music;

import com.github.instagram4j.Instagram4J.models.music.IGMusicLyrics;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMusicTrackLyricsResponse extends IGResponse {
    private IGMusicLyrics lyrics;
}
