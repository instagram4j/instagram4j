package weco.results;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * @author Weco
 *
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstagramCommentsResult {
	    public List<InstagramCommentResult> comments;
	    public boolean has_more_comments;
	    public String status;
	    public int comment_count;
	    public boolean comment_likes_enabled;
	    public boolean has_more_headload_comments;	
	    public String next_max_id;
	    
	    @Data
	    @JsonIgnoreProperties(ignoreUnknown = true)
	    public static class InstagramCommentResult {
	    	public String status;
	    	public String content_type;
	    	public boolean has_liked_comment;
	    	public int comment_like_count;
	    	public int bit_flags;
	    	public String text;
	    	public int type;
	    	public long created_at;
	    	public long created_at_utc;
	    	public long user_id;
	    	public long pk;
	    	public Map<String, Object> user;
	    }
	    
}
