package com.jxinsta.mobile.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Constant values used throughout the mobile module, including API URLs, endpoints, and default headers.
 */
public class Constants {
    /** The base URL for the Instagram Mobile API. */
    public static final String BASE_URL = "https://i.instagram.com/api/v1/";
    /** The endpoint for performing AJAX-based web login. */
    public static final String LOGIN_ENDPOINT = "web/accounts/login/ajax/";
    /** The default User-Agent string mimicking an Android device for mobile API calls. */
    public static final String MOBILE_USER_AGENT = "Instagram 347.3.0.41.106 Android";
    /** Default headers used for most API requests. */
    public static final Map<String,String> BASE_HEADERS = new HashMap<>(){
        {
            put("authority", "www.instagram.com");
            put("accept", "application/json");
            put("origin", "https://www.instagram.com");
            put("content-type", "application/x-www-form-urlencoded");
            put("user-agent", MOBILE_USER_AGENT);
            put("referer", "https://www.instagram.com/");
        }
    };
    
    /**
     * Inner class containing relative endpoint paths and path generators.
     */
    public static class Endpoints {
        /** Endpoint for the main timeline feed. */
        public static final String FEED_POSTS = "feed/timeline/";
        /** Endpoint for fetching the story reel tray. */
        public static final String STORY = "feed/reels_tray/?is_following_feed=false";
        /** Endpoint for liking a story. */
        public static final String STORY_LIKE = "story_interactions/send_story_like";
        /** Endpoint for unliking a story. */
        public static final String STORY_UNLIKE = "story_interactions/unsend_story_unlike";
        /** Endpoint for fetching the reels tray. */
        public static final String REELS_TRAY = "feed/reels_tray/";
        /** Endpoint for fetching clips items. */
        public static final String CLIPS_ITEMS = "clips/items/";
        /** Endpoint for configuring/publishing uploaded media. */
        public static final String MEDIA_CONFIGURE = "media/configure/";
        /** Endpoint for searching users. */
        public static final String USER_SEARCH = "users/search/";
        /** Endpoint for sending text direct messages. */
        public static final String SEND_MESSAGE = "direct_v2/threads/broadcast/text/";
        /** Endpoint for sending photo direct messages. */
        public static final String SEND_PHOTO = "direct_v2/threads/broadcast/photo_attachment/";
        /** Endpoint for fetching active (online) users. */
        public static final String ONLINE_USERS = "direct_v2/get_presence_active_now/?recent_thread_limit=0&suggested_followers_limit=100";
        /** Endpoint for pending DM requests. */
        public static final String DM_REQUESTS = "direct_v2/pending_inbox/";
        /** Endpoint for the main DM inbox. */
        public static final String DM_INBOX = "direct_v2/inbox/";

        /** Returns the like endpoint for a specific media ID. */
        public static String mediaLike(String id) {
            return "media/" + id + "/like/";
        }

        /** Returns the endpoint to hide/delete a DM thread. */
        public static String deleteThread(String id){
            return "direct_v2/threads/" + id + "/hide/";
        }

        /** Returns the endpoint for fetching a specific DM thread's details. */
        public static String getThread(String id){
            return "direct_v2/threads/" + id + "/";
        }

        /** Returns the endpoint to mark a DM item as seen. */
        public static String markSeen(String thread,String item){
            return "direct_v2/threads/" + thread + "/items/" + item + "/seen/";
        }

        /** Returns the unlike endpoint for a specific media ID. */
        public static String mediaUnlike(String id) {
            return "media/" + id + "/unlike/";
        }

        /** Returns the endpoint for fetching likers of a specific media ID. */
        public static String mediaLikers(String id) {
            return "media/" + id + "/likers/";
        }

        /** Returns the comment endpoint for a specific media ID. */
        public static String mediaComment(String id) {
            return "media/" + id + "/comment/";
        }

        /** Returns the endpoint for fetching a user's story/reel media by PK. */
        public static String userReelMedia(String pk) {
            return "feed/user/" + pk + "/reel_media/";
        }

        /** Returns the endpoint for fetching a user's highlights tray by PK. */
        public static String userHighlightsTray(String pk) {
            return "highlights/" + pk + "/highlights_tray/";
        }

        /** Returns the endpoint for following a user by PK. */
        public static String friendshipsCreate(String pk) {
            return "friendships/create/" + pk + "/";
        }

        /** Returns the endpoint for unfollowing a user by PK. */
        public static String friendshipsDestroy(String pk) {
            return "friendships/destroy/" + pk + "/";
        }

        /** Returns the endpoint for blocking a user by PK. */
        public static String friendshipsBlock(String pk) {
            return "friendships/block/" + pk + "/";
        }

        /** Returns the endpoint for fetching user info by username. */
        public static String userInfo(String username) {
            return "users/" + username + "/usernameinfo/";
        }

        /** Returns the endpoint for fetching media information by media ID. */
        public static String mediaInfo(String id) {
            return "media/" + id + "/info/";
        }

        /** Returns the endpoint for liking a comment by comment ID. */
        public static String commentLike(String id) {
            return "media/" + id + "/comment_like/";
        }

        /** Returns the endpoint for unliking a comment by comment ID. */
        public static String commentUnlike(String id) {
            return "media/" + id + "/comment_unlike/";
        }

        /** Returns the endpoint for bulk deleting comments on a specific media ID. */
        public static String commentBulkDelete(long id) {
            return "media/" + id + "/comment/bulk_delete/";
        }

        /** Returns the endpoint for web-based profile info. */
        public static String webProfileInfo(String username) {
            return "users/web_profile_info/?username=" + username;
        }

        /** Returns the endpoint for fetching hashtag information. */
        public static String hashtagInfo(String tag) {
            return "tags/logged_out_web_info/?tag_name=" + tag;
        }
    }
}
