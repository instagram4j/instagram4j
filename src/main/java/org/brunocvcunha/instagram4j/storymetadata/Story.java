package org.brunocvcunha.instagram4j.storymetadata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by bvn13 on 02.06.2019.
 */
@Getter
@Setter
@ToString
public class Story {

    private boolean can_reply;
    private boolean can_reshare;
    private long expiring_at;
    private String id;
    private List<Object> items; //don't know what it is
    private long latest_reel_media;
    private StoryLocation location;
    private StoryLocation owner;
    private int prefetch_count;
    private String reel_type;
    private int seen;
    private long unique_integer_reel_id;

}
