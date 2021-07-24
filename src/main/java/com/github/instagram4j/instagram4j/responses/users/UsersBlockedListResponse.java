package com.github.instagram4j.instagram4j.responses.users;

import java.util.List;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import lombok.Data;

@Data
public class UsersBlockedListResponse extends IGResponse {
	private List<BlockedUser> blocked_list;
	
	@Data
	public static class BlockedUser{
	    public long user_id;
	    public String username;
	    public String full_name;
	    public String profile_pic_url;
	    public long block_at;
	    public boolean is_auto_block_enabled;
	}
}
