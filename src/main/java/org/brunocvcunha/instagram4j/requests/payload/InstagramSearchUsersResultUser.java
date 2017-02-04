package org.brunocvcunha.instagram4j.requests.payload;

import java.util.Map;

import lombok.Data;

@Data
public class InstagramSearchUsersResultUser {

    public int unseen_count;
    public String username;
    public String byline;
    public String profile_pic_url;
    public boolean has_anonymous_profile_picture;
    public Map<String, Object> friendship_status;
    public boolean is_private;
    public boolean is_verified;
    public int mutual_followers_count;
    public String full_name;
    public long pk;
    public int follower_count;
    public String profile_pic_id;
    public String social_context;
    public String search_social_context;

}