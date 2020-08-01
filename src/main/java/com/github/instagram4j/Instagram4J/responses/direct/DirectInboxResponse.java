package com.github.instagram4j.Instagram4J.responses.direct;

import com.github.instagram4j.Instagram4J.models.direct.Inbox;
import com.github.instagram4j.Instagram4J.models.user.User;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class DirectInboxResponse extends IGResponse {
    private User viewer;
    private Inbox inbox;
    private int seq_id;
    private int pending_requests_total;
    private User most_recent_inviter;
}
