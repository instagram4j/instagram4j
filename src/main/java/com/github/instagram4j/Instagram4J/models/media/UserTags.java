package com.github.instagram4j.Instagram4J.models.media;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.user.Profile;

import lombok.Data;

@Data
public class UserTags extends IGBaseModel {
    private List<UserTag> in;
    
    @Data
    public static class UserTag {
        private Profile user;
        private double[] position;
        private long start_time_in_video_sec;
        private long duration_in_video_in_sec;
    }
    
    @Data
    public static class UserTagPayload {
        private final long user_id;
        private final double[] position;
        
        public UserTagPayload(long pk, double x, double y) {
            this.user_id = pk;
            position = new double[] {x, y};
        }
    }
}
