package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

public class InstagramPostLiveItem {
    private long pk;
    private InstagramUser user;
    private List<InstagramBroadcast> broadcasts;
    private Object last_seen_broadcast_ts;
    private Object can_reply;
    private Object ranked_position;
    private Object seen_ranked_position;
    private Object muted;
    private Object can_reshare;
}
