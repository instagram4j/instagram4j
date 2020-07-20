package com.github.instagram4j.Instagram4J.models.music;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;

@Data
public class IGMusicLyrics {
    private List<IGLyricPhrase> phrases;
    
    @Data
    public static class IGLyricPhrase extends IGBaseModel {
        private long start_time_in_ms;
        private long end_time_in_ms;
        private String phrase;
    }
}
