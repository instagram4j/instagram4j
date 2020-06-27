package com.github.instagram4j.Instagram4J.models.direct;

import java.util.List;

import lombok.Data;

@Data
public class IGInbox {
    private List<IGThread> threads;
    private boolean has_older;
    private int unseen_count;
    private long unseen_count_ts;
    private IGCursor prev_cursor;
    private IGCursor next_cursor;
    private boolean blended_inbox_enabled;
    
    @Data
    public static class IGCursor {
        private long cursor_timestamp_seconds;
        private long cursor_thread_v2_id;
    }
}
