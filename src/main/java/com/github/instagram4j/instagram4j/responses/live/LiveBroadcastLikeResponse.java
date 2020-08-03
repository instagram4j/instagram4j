package com.github.instagram4j.instagram4j.responses.live;

import java.util.List;

import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class LiveBroadcastLikeResponse extends IGResponse {
    private String likes;
    private String burst_likes;
    
    @Data
    public static class LiveBroadcastGetLikeCountResponse extends LiveBroadcastLikeResponse {
        private long like_ts;
        private List<Liker> likers;
        
        @Data
        public static class Liker {
            private String user_id;
            private String profile_pic_url;
            private int count;
        }
    }
}
