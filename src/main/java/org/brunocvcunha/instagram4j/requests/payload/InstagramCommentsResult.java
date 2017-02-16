package weco.main;

import java.util.List;

import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchTagsResultTag;

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
}
