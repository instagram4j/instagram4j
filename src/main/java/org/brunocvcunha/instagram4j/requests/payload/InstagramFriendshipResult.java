package org.brunocvcunha.instagram4j.requests.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instagram friendship response, at the moment used only as approve/reject requests result
 * 
 * @author Daniele Pancottini
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramFriendshipResult extends StatusResult {

	public InstagramFriendshipStatus friendship_status;
	
}
