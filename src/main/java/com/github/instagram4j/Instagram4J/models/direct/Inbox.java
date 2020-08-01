package com.github.instagram4j.Instagram4J.models.direct;

import java.util.List;

import lombok.Data;

@Data
public class Inbox {
    private List<Thread> threads;
    private boolean has_older;
    private int unseen_count;
    private long unseen_count_ts;
    private Cursor prev_cursor;
    private Cursor next_cursor;
    private boolean blended_inbox_enabled;

    @Data
    public static class Cursor {
        private String cursor_timestamp_seconds;
        private String cursor_thread_v2_id;
    }
}
