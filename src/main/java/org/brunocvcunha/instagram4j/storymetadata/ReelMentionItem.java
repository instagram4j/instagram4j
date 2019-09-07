package org.brunocvcunha.instagram4j.storymetadata;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * 
 * @author Justin
 *
 */
@Getter
@Setter
public class ReelMentionItem extends StoryItem {
    private InstagramUser user;
}
