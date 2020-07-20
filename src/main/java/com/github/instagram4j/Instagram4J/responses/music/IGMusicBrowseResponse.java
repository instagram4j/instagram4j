package com.github.instagram4j.Instagram4J.responses.music;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.instagram4j.Instagram4J.models.music.IGMusicPlaylist;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Data;

@Data
public class IGMusicBrowseResponse extends IGResponse {
    @JsonDeserialize(converter = BeanToIGMusicPlaylistConverter.class)
    private List<IGMusicPlaylist> items;
    
    private static class BeanToIGMusicPlaylistConverter extends StdConverter<List<Map<String, Object>>, List<IGMusicPlaylist>> {
        @Override
        public List<IGMusicPlaylist> convert(List<Map<String, Object>> value) {
            return value.stream()
                    .filter(m -> m.containsKey("playlist"))
                    .map(m -> IGUtils.MAPPER.convertValue(m.get("playlist"), IGMusicPlaylist.class))
                    .collect(Collectors.toList());
        }
    }
}
