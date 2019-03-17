package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.ToString;

/**
 * Location Feed Result Section
 * @author jpleorx
 */
@Getter
@ToString(callSuper = true)
public class InstagramLocationFeedResultSection {
    private String layout_type;
    private InstagramLocationFeedResultSectionLayoutContent layout_content;
}