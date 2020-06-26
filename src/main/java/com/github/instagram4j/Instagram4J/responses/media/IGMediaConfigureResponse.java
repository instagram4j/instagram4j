package com.github.instagram4j.Instagram4J.responses.media;

import com.github.instagram4j.Instagram4J.models.media.IGMedia;
import com.github.instagram4j.Instagram4J.models.reelmedia.IGReelMedia;
import com.github.instagram4j.Instagram4J.models.timelinemedia.IGTimelineMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGMediaConfigureResponse extends IGResponse {
    private IGMedia media;
    
    @Data
    public static class IGMediaConfigureTimelineResponse extends IGMediaConfigureResponse {
        private IGTimelineMedia media;
    }
    
    @Data
    public static class IGMediaConfigureSidecarResponse extends IGMediaConfigureTimelineResponse {
        private String client_sidecar_id;
    }

    @Data
    public static class IGMediaConfigureToStoryResponse extends IGMediaConfigureResponse {
        private IGReelMedia media;
    }
}