package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.responses.IGPaginatedResponse;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Data;

@Data
public class IGFeedTimelineResponse extends IGPaginatedResponse {
    private boolean auto_load_more_enabled;
    private int num_results;
    private String next_max_id;
    @JsonDeserialize(converter = FilterToIGTimelineMedia.class)
    private List<IGTimelineMedia> feed_items;
    private boolean more_available;
    
    private static class FilterToIGTimelineMedia extends StdConverter<List<Map<String, Object>>, List<IGTimelineMedia>> {
        @Override
        public List<IGTimelineMedia> convert(List<Map<String, Object>> value) {
            return value.stream()
                    .filter(m -> m.containsKey("media_or_ad"))
                    .map(m -> IGUtils.MAPPER.convertValue(m.get("media_or_ad"), IGTimelineMedia.class))
                    .collect(Collectors.toList());
        }
    }
}