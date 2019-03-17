package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.ToString;

/**
 * Location Feed Result
 * @author jpleorx
 */
@Getter
@ToString(callSuper = true)
public class InstagramLocationFeedResult {
    private InstagramLocationFeedResultSection[] sections;
}