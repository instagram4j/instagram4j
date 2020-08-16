package com.github.instagram4j.instagram4j.models.music;

import java.util.List;

import com.github.instagram4j.instagram4j.models.IGBaseModel;

import lombok.Data;

@Data
public class MusicLyrics {
    private List<LyricPhrase> phrases;

    @Data
    public static class LyricPhrase extends IGBaseModel {
        private long start_time_in_ms;
        private long end_time_in_ms;
        private String phrase;
    }
}
