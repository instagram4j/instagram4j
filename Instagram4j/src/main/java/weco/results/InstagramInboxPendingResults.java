package weco.results;

import java.util.List;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * @author Weco
 *
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstagramInboxPendingResults {
	public Inbox inbox;
	public String status;
	public int seq_id;
	public int pending_requests_total;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Inbox {
		public boolean has_older;
		public List<Messages> threads;
		public int unseen_count;
		public long unseen_count_ts;

		@JsonIgnore
		public List<Messages> getMessages(){
			return threads;
		}
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Messages {
		public List<Message> items;
		public boolean muted;
		public String newest_cursor;
		public boolean canonical;
		public boolean has_older;
		public String oldest_cursor;
		public long last_activity_at;
		public String thread_title;
		public boolean is_spam;
		public String thread_id;
		public String thread_type;
		public boolean named;
		public boolean has_newer;
		public long viewer_id;
		public List<FriendshipStatus> users;
		public InstagramUserSummary inviter;
		public Object left_users; //No idea what this is, always got an empty result.

		@JsonIgnore
		public List<Message> getMessages(){
			return items;
		}

		@Data
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Message {
			public String item_id;
			public String text;
			public long timestamp;
			public long user_id;
			public String item_type;
		}

		/*@Data
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class InstagramUserSummaryExtended extends InstagramUserSummary{
			public Map<String, Object> friendship_status;
		}*/

	}

}
