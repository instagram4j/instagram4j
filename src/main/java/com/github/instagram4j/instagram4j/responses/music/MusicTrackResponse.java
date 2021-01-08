package com.github.instagram4j.instagram4j.responses.music;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.instagram4j.instagram4j.models.music.MusicPlaylist.BeanToTrackConverter;
import com.github.instagram4j.instagram4j.models.music.MusicTrack;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

@Data
public class MusicTrackResponse extends IGResponse implements IGPaginatedResponse {
    @JsonDeserialize(converter = BeanToTrackConverter.class)
    private List<MusicTrack> items;
    private MusicTrackPageInfo page_info;

    public String getNext_max_id() {
        return page_info.getNext_max_id();
    }

    public boolean isMore_available() {
        return page_info.isMore_available();
    }

    @Data
    public static class MusicTrackPageInfo {
        private String next_max_id;
        private boolean more_available;
    }
}
