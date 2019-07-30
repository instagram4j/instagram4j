package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Result object for the "hidden" instagram inbox containing pending direct messages
 *
 * @author jemand771
 */
@Getter
@Setter
@ToString(callSuper = true)
public class InstagramPendingInboxResult extends StatusResult {

    public String seq_id;
    public int pending_requests_total;
    public InstagramInbox inbox;
}