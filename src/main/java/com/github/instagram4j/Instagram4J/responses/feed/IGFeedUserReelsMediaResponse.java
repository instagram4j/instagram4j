package com.github.instagram4j.Instagram4J.responses.feed;

import java.util.List;

import com.github.instagram4j.Instagram4J.models.IGUser;
import com.github.instagram4j.Instagram4J.models.reelmedia.IGReelMedia;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGFeedUserReelsMediaResponse extends IGResponse {
    private String id;
    private String latest_reel_media;
    private String seen;
    private List<IGReelMedia> items;
    private IGUser user;
    private Object broadcast;
}
