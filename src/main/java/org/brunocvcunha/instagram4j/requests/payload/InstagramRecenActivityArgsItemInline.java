package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramRecenActivityArgsItemInline {
    public boolean following;
    public boolean outgoing_request;
    public InstagramUserSummary user_info;
}
