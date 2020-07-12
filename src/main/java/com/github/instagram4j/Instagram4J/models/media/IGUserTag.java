package com.github.instagram4j.Instagram4J.models.media;

import lombok.Data;

@Data
public class IGUserTag {
    private final long user_id;
    private final double[] position;
    
    public IGUserTag(long pk, double x, double y) {
        this.user_id = pk;
        position = new double[] {x, y};
    }
}
