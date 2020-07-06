package com.github.instagram4j.Instagram4J.responses.direct;

import java.util.Map;

import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGDirectGetPresenceResponse extends IGResponse {
    private Map<Long, IGUserPresence> user_presence;
    
    @Data
    public static class IGUserPresence {
        private boolean is_active;
        private long last_activity_at_ms;
    }
}
