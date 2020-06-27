package com.github.instagram4j.Instagram4J.responses.direct;

import com.github.instagram4j.Instagram4J.models.direct.IGInbox;
import com.github.instagram4j.Instagram4J.models.user.IGUser;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGDirectInboxResponse extends IGResponse {
    private IGUser viewer;
    private IGInbox inbox;
    private int seq_id;
    private int pending_requests_total;
    private IGUser most_recent_inviter;
}
