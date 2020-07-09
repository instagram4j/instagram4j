package com.github.instagram4j.Instagram4J.responses.media;

import com.github.instagram4j.Instagram4J.models.media.IGMedia;
import com.github.instagram4j.Instagram4J.models.media.reel.IGReelMedia;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineVideoMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMediaResponse extends IGResponse {
    private IGMedia media;

    @Data
    public static class IGMediaConfigureTimelineResponse extends IGMediaResponse {
        private IGTimelineMedia media;
    }

    @Data
    public static class IGMediaConfigureSidecarResponse extends IGMediaConfigureTimelineResponse {
        private String client_sidecar_id;
    }

    @Data
    public static class IGMediaConfigureToStoryResponse extends IGMediaResponse {
        private IGReelMedia media;
    }
    
    @Data
    public static class IGMediaConfigureToIgtvResponse extends IGMediaResponse {
        private IGTimelineVideoMedia media;
    }
}