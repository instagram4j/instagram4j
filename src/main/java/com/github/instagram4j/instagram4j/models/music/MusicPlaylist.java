package com.github.instagram4j.instagram4j.models.music;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.instagram4j.instagram4j.models.IGBaseModel;
import com.github.instagram4j.instagram4j.utils.IGUtils;

import lombok.Data;

@Data
public class MusicPlaylist extends IGBaseModel {
    private String id;
    private String title;
    private String icon_url;
    @JsonDeserialize(converter = BeanToTrackConverter.class)
    private List<MusicTrack> preview_items;

    public static class BeanToTrackConverter
            extends StdConverter<List<Map<String, Object>>, List<MusicTrack>> {
        @Override
        public List<MusicTrack> convert(List<Map<String, Object>> value) {
            return value.stream()
                    .filter(m -> m.containsKey("track"))
                    .map(m -> IGUtils.convertToView(m.get("track"), MusicTrack.class))
                    .collect(Collectors.toList());
        }
    }
}
