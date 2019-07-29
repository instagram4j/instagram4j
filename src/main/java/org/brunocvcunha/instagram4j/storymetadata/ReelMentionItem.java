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
public class ReelMentionItem {
    private double x;
    private double y;
    private double z;
    private double width;
    private double height;
    private double rotation;
    private int is_pinned;
    private int is_hidden;
    private int is_sticker;
    private InstagramUser user;
}
