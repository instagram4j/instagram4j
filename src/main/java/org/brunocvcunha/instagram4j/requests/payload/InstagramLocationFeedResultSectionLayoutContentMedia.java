package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.ToString;

/**
 * Location Feed Result Section Layout Content Media
 * @author jpleorx
 */
@Getter
@ToString(callSuper = true)
public class InstagramLocationFeedResultSectionLayoutContentMedia {
    private InstagramFeedItem media;
}