package org.brunocvcunha.instagram4j.requests.payload;

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
public class InstagramCommentResult {
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
