package com.github.instagram4j.instagram4j.models.clips;

import lombok.Data;

@Data
public class ServerDrivenCacheConfig {
    private Boolean serve_from_server_cache = true;
    private String cohort_to_ttl_map = "";
    private String serve_on_foreground_prefetch = "true";
    private String serve_on_background_prefetch = "true";
    private String meta = "";
}
