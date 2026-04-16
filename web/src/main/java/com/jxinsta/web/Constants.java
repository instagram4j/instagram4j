package com.jxinsta.web;

import com.jxinsta.web.endpoints.post.Post;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String BASE_URL = "https://www.instagram.com/api/v1/";
    public static final String LOGIN_URL = BASE_URL + "web/accounts/login/ajax/";
    public static final String MOBILE_USER_AGENT = "Instagram 347.3.0.41.106";
    public static final String WEB_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/146.0.0.0 Safari/537.36";
    public static final String APP_ID = "936619743392459";
    public static final Map<String,String> BASE_HEADERS = new HashMap<>(){
        {
            put("authority", "www.instagram.com");
            put("accept", "application/json");
            put("origin", "https://www.instagram.com");
            put("content-type", "application/x-www-form-urlencoded");
            put("referer", "https://www.instagram.com/");
            put("sec-fetch-site", "same-origin");
        }
    };
    public static final Map<Integer, Post.MEDIA_TYPE> mediaTypes = new HashMap<>(){
        {
            put(1, Post.MEDIA_TYPE.IMAGE);
            put(2, Post.MEDIA_TYPE.VIDEO);
            put(8, Post.MEDIA_TYPE.CAROUSEL);
        }
    };


    public static class GraphQl {
        public static final String BASE_URL = "https://www.instagram.com/graphql/query/?query_id=";
        public static final String USER_FOLLOWING = "17874545323001329";
        public static final String USER_FOLLOWERS = "17851374694183129";
        public static final String COMMENTS = "17852405266163336";
        public static final String LIKES = "17864450716183058";
        public static final String PUBLIC_USER_POSTS = "7950326061742207";
        public static final String FEED_POSTS = "9577944065588204";
        public static final String HIGHLIGHTS = "9719220061527365";
        public static final String STORY = "9558504827580036";
        public static final String PROFILE_POSTS = "9806959572732215";
        public static final String FOLLOW = "9663809173698092";
        public static final String UNFOLLOW = "9587989991322522";
        public static final String BLOCK = "9706706379436985";
        public static final String USER_STORY = "9551577494970907";
        public static final String PROFILE = "9661599240584790";
        public static final String HASHTAG = "33897670563213924";
    }

    public static class Endpoints {
        public static final String MEDIA_CONFIGURE = "media/configure/";
        public static final String STORIES = "feed/reels_tray/?is_following_feed=true";
        public static final String SEARCH = "web/search/topsearch/?context=user&count=0&query=";

        public static String userInfo(String username) {
            return "users/" + username + "/usernameinfo/";
        }

        public static String mediaInfo(String id) {
            return "media/" + id + "/info/";
        }

        public static String comments(String mediaId) {
            return "media/" + mediaId + "/comments/?can_support_threading=true&permalink_enabled=false";
        }

        public static String commentAction(String id, String action) {
            return "web/comments/" + action + "/" + id + "/";
        }

        public static String postAction(String id, String action) {
            return "web/likes/" + id + "/" + action + "/";
        }

        public static String likers(String id) {
            return "media/" + id + "/likers/";
        }
    }
}
