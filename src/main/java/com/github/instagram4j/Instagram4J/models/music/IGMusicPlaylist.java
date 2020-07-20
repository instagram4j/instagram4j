package com.github.instagram4j.Instagram4J.models.music;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Data;

@Data
public class IGMusicPlaylist extends IGBaseModel {
    private String id;
    private String title;
    private String icon_url;
    @JsonDeserialize(converter = BeanToTrackConverter.class)
    private List<IGMusicTrack> preview_items;
    
    public static class BeanToTrackConverter extends StdConverter<List<Map<String, Object>>, List<IGMusicTrack>> {
        @Override
        public List<IGMusicTrack> convert(List<Map<String, Object>> value) {
            return value.stream()
                    .filter(m -> m.containsKey("track"))
                    .map(m -> IGUtils.MAPPER.convertValue(m.get("track"), IGMusicTrack.class))
                    .collect(Collectors.toList());
        }
    }
}
