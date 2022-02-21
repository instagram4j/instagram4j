package com.github.instagram4j.instagram4j.responses.feed;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.github.instagram4j.instagram4j.models.clips.PagingInfo;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineVideoMedia;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class FeedClipsResponse extends IGResponse {
    @JsonDeserialize(converter = MediaBeanToClipMedia.class)
    private List<TimelineVideoMedia> items;
    private PagingInfo paging_info;
    private String status;


    private static class MediaBeanToClipMedia
            extends StdConverter<List<Map<String, Object>>, List<TimelineVideoMedia>> {
        @Override
        public List<TimelineVideoMedia> convert(List<Map<String, Object>> value) {
            return value.stream()
                    .filter(m -> m.containsKey("media"))
                    .map(m -> IGUtils.convertToView(m.get("media"), TimelineVideoMedia.class))
                    .collect(Collectors.toList());
        }
    }
}
