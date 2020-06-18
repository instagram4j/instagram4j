package com.github.instagram4j.Instagram4J.models;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class IGMediaItem {
	private long taken_at;
    private long pk;
    private String id;
    private long device_timestamp;
    private int media_type;
    private String code;
    private String client_cache_key;
    private int filter_type;
    private boolean has_audio;
    private double video_duration;
    private Map<String, Object> attribution;
    //private List<ImageMeta> video_versions;
    //private ImageVersions image_versions2;
    //private InstagramUserTagsContainer usertags;
    //private FeedItemLocation location;
    private float lng;
    private float lat;
    private int original_width;
    private int original_height;
    private int view_count;
    private IGUser user;
    //private List<InstagramCarouselMediaItem> carousel_media;
    private String organic_tracking_token;
    private int like_count;
    private List<String> top_likers;
    //private List<InstagramUserSummary> likers;
    private boolean has_liked;
    private boolean comment_likes_enabled;
    private boolean has_more_comments;
    private long next_max_id;
    private int max_num_visible_preview_comments;
    private List<IGComment> preview_comments;
    private List<Object> comments;
    private int comment_count;
    private IGComment caption;
    private boolean can_viewer_reshare;
    private boolean caption_is_edited;
    private boolean photo_of_you;
    private boolean comments_disabled;
    private boolean can_viewer_save;
    private boolean has_viewer_saved;
}
