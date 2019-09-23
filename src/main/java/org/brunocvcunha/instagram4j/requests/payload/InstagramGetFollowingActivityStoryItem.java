package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Story Item
 * Small investigation of feed shows these actions:
 * <p>
 * Story type describes type of content
 * 13	comment
 * 60	post
 * 101	user
 * 128	location
 * 12	post
 * <p>
 * Type describes action
 * 1	like concrete person   (concrete person)
 * 2	like several posts
 * 4	following multiple
 * 14	like comment
 */
@Getter
@Setter
@ToString
public class InstagramGetFollowingActivityStoryItem {

    private Long type;
    private Long story_type;
    private InstagramGetFollowingActivityStoryArgs args;
    private Object counts;
    private String pk;

}
