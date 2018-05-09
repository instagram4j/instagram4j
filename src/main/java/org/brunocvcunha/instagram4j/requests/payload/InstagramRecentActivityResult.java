package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instagram Recent Activity Result
 * 
 * @author Krisnamourt da Silva C. Filho
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramRecentActivityResult {

	private InstagramRecentActivity aymf;
	private InstagramRecentActivityCounts counts;
	private List<InstagramStory> friend_request_stories;
	private List<InstagramStory> new_stories;
	private List<InstagramStory> old_stories;
	private int continuation_token;
	private String subscription;
	private String status;
	
}
