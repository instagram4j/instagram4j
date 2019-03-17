package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.ToString;

/**
 * Location Feed Result Section Layout Content
 * @author jpleorx
 */
@Getter
@ToString(callSuper = true)
public class InstagramLocationFeedResultSectionLayoutContent {
    private InstagramLocationFeedResultSectionLayoutContentMedia[] medias;
}