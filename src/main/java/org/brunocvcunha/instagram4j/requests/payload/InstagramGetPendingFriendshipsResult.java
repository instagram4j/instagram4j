package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**ù
 *	Get pending friendships result
 *
 * @author Daniele Pancottini
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramGetPendingFriendshipsResult extends StatusResult {

	
	public List<InstagramUser> users;
	public int truncate_follow_requests_at_index;
	public String next_max_id;
	
	/*
	  	fields not necessary to response
	 
		public InstagramSuggestedUsers suggested_users;
		public int page_size;
		public int big_list;
		
	 */
	 
}
