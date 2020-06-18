package com.github.instagram4j.Instagram4J.models;

import lombok.Data;

@Data
public class IGFeedItem {
	private IGMediaItem media_or_ad;
	private Object suggested_users;
	private Object end_of_feed_demarcator;
}
