package com.github.instagram4j.Instagram4J.responses.media;

import com.github.instagram4j.Instagram4J.models.media.Media;
import com.github.instagram4j.Instagram4J.models.media.reel.ReelMedia;
import com.github.instagram4j.Instagram4J.models.media.timeline.TimelineMedia;
import com.github.instagram4j.Instagram4J.models.media.timeline.TimelineVideoMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class MediaResponse extends IGResponse {
    private Media media;

    @Data
    public static class MediaConfigureTimelineResponse extends MediaResponse {
        private TimelineMedia media;
    }

    @Data
    public static class MediaConfigureSidecarResponse extends MediaConfigureTimelineResponse {
        private String client_sidecar_id;
    }

    @Data
    public static class MediaConfigureToStoryResponse extends MediaResponse {
        private ReelMedia media;
    }
    
    @Data
    public static class MediaConfigureToIgtvResponse extends MediaResponse {
        private TimelineVideoMedia media;
    }
}