package org.brunocvcunha.instagram4j.requests.payload;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 *
 * Data class which represents response of {@link org.brunocvcunha.instagram4j.requests.InstagramGetUserReelMediaFeedRequest} (users's story/ies)
 *
 * @author Daniele Pancottini
 *
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramUserReelMediaFeedResult extends StatusResult{

    public String id;
    public String latest_reel_media;
    public String seen;
    public boolean can_reply;
    public boolean can_reshare;
    public String reel_type;
    public InstagramUser user;
    public List<InstagramItem> items;
    public String ranked_position;
    public String seen_ranked_position;
    public String expiring_at;
    public boolean has_besties_media;
    public InstagramLocation location;
    public int prefetch_count;
    public InstagramBroadcast broadcast;

}
