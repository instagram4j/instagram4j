package com.github.instagram4j.Instagram4J.models.feed;

import com.github.instagram4j.Instagram4J.models.media.timeline.IGTimelineMedia;

import lombok.Data;

@Data
public class IGFeedItem {
    private IGTimelineMedia media_or_ad;
    private Object suggested_users;
    private Object end_of_feed_demarcator;
}