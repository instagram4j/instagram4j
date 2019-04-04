package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Recent Activity Results
 *
 * @author Anthony E Rodriguez
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramRecentActivityArgsItem {
    public String text;
    public String profile_image;
    public String profile_name;
    public String timestamp;
    public boolean clicked;
    public InstagramRecenActivityArgsItemInline inline_follow;
}
