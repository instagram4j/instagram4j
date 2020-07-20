package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Data;

@Data
public class IGFeedSavedResponse extends IGResponse {
    @JsonDeserialize(converter = MediaBeanToIGTimelineMedia.class)
    private List<IGTimelineMedia> items;
    
    private class MediaBeanToIGTimelineMedia extends StdConverter<List<Map<String, Object>>, List<IGTimelineMedia>> {
        @Override
        public List<IGTimelineMedia> convert(List<Map<String, Object>> value) {
            return value.stream()
                    .filter(m -> m.containsKey("media"))
                    .map(m -> IGUtils.MAPPER.convertValue(m.get("media"), IGTimelineMedia.class))
                    .collect(Collectors.toList());
        }
    }
}
