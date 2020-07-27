package com.github.instagram4j.Instagram4J.models.discover;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Data;

@Data
@JsonTypeName("media_grid")
public class IGSectionalMediaGridItem extends IGSectionalItem {
    @JsonProperty("layout_content")
    @JsonDeserialize(converter = LayoutContentToIGTimelineMedia.class)
    private List<IGTimelineMedia> medias;
    
    private static class LayoutContentToIGTimelineMedia extends StdConverter<Map<String, List<Map<String, Object>>>, List<IGTimelineMedia>> {
        @Override
        public List<IGTimelineMedia> convert(Map<String, List<Map<String, Object>>> value) {
            return value.get("medias").stream()
                    .map(m -> IGUtils.MAPPER.convertValue(m.get("media"), IGTimelineMedia.class))
                    .collect(Collectors.toList());
        }
    }
}
