package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * story_share for direct messages
 * @author Willy Hille
 */
@Getter
@Setter
@ToString
public class StoryShare {

    public InstagramFeedItem media;
    public String text;
    public String title;
    public String message;
    public boolean is_linked;
}
