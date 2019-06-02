package org.brunocvcunha.instagram4j.requests.payload;

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
public class InstagramStory {

    private boolean can_reply;
    private boolean can_reshare;
    private long expiring_at;
    private String id;

    private List<InstagramStoryItem> items;

    private long latest_reel_media;

    private InstagramLocation location;

}
