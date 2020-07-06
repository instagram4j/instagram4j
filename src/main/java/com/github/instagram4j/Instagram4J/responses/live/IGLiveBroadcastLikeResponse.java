package com.github.instagram4j.Instagram4J.responses.live;

import java.util.List;

import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGLiveBroadcastLikeResponse extends IGResponse {
    private String likes;
    private String burst_likes;
    
    @Data
    public static class IGLiveBroadcastGetLikeCountResponse extends IGLiveBroadcastLikeResponse {
        private long like_ts;
        private List<IGLiker> likers;
        
        @Data
        public static class IGLiker {
            private String user_id;
            private String profile_pic_url;
            private int count;
        }
    }
}
