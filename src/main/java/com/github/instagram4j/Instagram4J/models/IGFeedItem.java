package com.github.instagram4j.Instagram4J.models;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class IGFeedItem {
	public long taken_at;
    public long pk;
    public String id;
    public long device_timestamp;
    public int media_type;
    public String code;
    public String client_cache_key;
    public int filter_type;
    public boolean has_audio;
    public double video_duration;
    public Map<String, Object> attribution;
    //public List<ImageMeta> video_versions;
    //public ImageVersions image_versions2;
    //public InstagramUserTagsContainer usertags;
    //public FeedItemLocation location;
    public float lng;
    public float lat;
    public int original_width;
    public int original_height;
    public int view_count;
    public IGUser user;
    //public List<InstagramCarouselMediaItem> carousel_media;
    public String organic_tracking_token;
    public int like_count;
    public List<String> top_likers;
    //public List<InstagramUserSummary> likers;
    public boolean has_liked;
    public boolean comment_likes_enabled;
    public boolean has_more_comments;
    public long next_max_id;
    public int max_num_visible_preview_comments;
    public List<IGComment> preview_comments;
    public List<Object> comments;
    public int comment_count;
    public IGComment caption;
    public boolean can_viewer_reshare;
    public boolean caption_is_edited;
    public boolean photo_of_you;
    public boolean comments_disabled;
    public boolean can_viewer_save;
    public boolean has_viewer_saved;
}
