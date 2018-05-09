package org.brunocvcunha.instagram4j.requests.payload;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instagram Story
 * 
 * @author Krisnamourt da Silva C. Filho
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramStory {

	private int type;
	private int story_type;
	private Map<String, Object> args;
	private Map<String, Object> inline_follow;
	private String profile_id;
	private String profile_image;
	private long timestamp;
	private String tuuid;
	private boolean clicked;
	private String profile_name;
	private InstagramRecentActivityCounts counts;
	private String pk;

}
