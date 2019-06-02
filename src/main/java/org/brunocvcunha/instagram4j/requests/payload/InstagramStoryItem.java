package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.brunocvcunha.instagram4j.storymetadata.StoryHashtag;
import org.brunocvcunha.instagram4j.storymetadata.StoryItemLocation;
import org.brunocvcunha.instagram4j.storymetadata.StoryVideoVersion;

import java.util.List;

/**
 * Created by bvn13 on 02.06.2019.
 */
@Getter
@Setter
@ToString
public class InstagramStoryItem {

    private boolean can_reply;
    private boolean can_reshare;
    private boolean can_viewer_save;
    private String caption;
    private boolean caption_is_edited;
    private double caption_position;
    private String client_cache_key;
    private String code;
    private long device_timestamp;
    private long expiring_at;
    private int filter_type;
    private boolean has_audio;
    private boolean has_shared_to_fb;
    private String id;
    private ImageVersions image_versions2;
    private long imported_taken_at;
    private int is_dash_eligible;
    private boolean is_reel_media;
    private int media_type;
    private int number_of_qualities;
    private String organic_tracking_token;
    private int original_height;
    private int original_width;
    private boolean photo_of_you;
    private long pk;
    private boolean show_one_tap_fb_share_tooltip;

    private List<StoryHashtag> story_hashtags;
    private List<StoryItemLocation> story_locations;

    private boolean supports_reel_reactions;
    private long taken_at;

    private InstagramUser user;
    private String video_codec;
    private String video_dash_manifest;
    private double video_duration;

    private List<StoryVideoVersion> video_versions;

}
