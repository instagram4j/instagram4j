package com.github.instagram4j.Instagram4J.models.friendships;

import lombok.Data;

@Data
public class IGFriendship {
    private boolean blocking;
    private boolean followed_by;
    private boolean following;
    private boolean incomng_request;
    private boolean is_bestie;
    private boolean is_blocking_reel;
    private boolean is_muting_reel;
    private boolean is_private;
    private boolean is_restricted;
    private boolean muting;
    private boolean outgoing_request;
}
