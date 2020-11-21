package com.github.instagram4j.instagram4j.responses.users;

import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.models.user.User;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import java.util.List;

import lombok.Data;

@Data
public class UsersSearchResponse extends IGResponse {

    private int num_results;
    private List<User> users;
    private boolean has_more;
    private String rank_token;
    private String page_token;

    @Data
    public static class User extends Profile {
        FriendshipStatus friendship_status;
        String social_context;
        String search_social_context;
        int mutual_followers_count;
        int latest_reel_media;
    }

    @Data
    public static class FriendshipStatus extends Profile {
        boolean following;
        boolean is_private;
        boolean incoming_request;
        boolean outgoing_request;
        boolean is_bestie;
        boolean is_restricted;
    }
}
