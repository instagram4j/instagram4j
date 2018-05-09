package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instagram Recent Activity Counts
 * 
 * @author Krisnamourt da Silva C. Filho
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramRecentActivityCounts {

	private int usertags;
	private int relationships;
	private int comments;
	private int likes;
	private int comment_likes;
	private int campaign_notification;
	private int photos_of_you;
	private int requests;
	
}
