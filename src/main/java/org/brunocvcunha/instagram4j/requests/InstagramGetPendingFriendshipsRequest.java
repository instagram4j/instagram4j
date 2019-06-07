package org.brunocvcunha.instagram4j.requests;

import org.brunocvcunha.instagram4j.requests.payload.InstagramGetPendingFriendshipsResult;

import lombok.SneakyThrows;

/**
 * {@link InstagramGetPendingFriendshipsRequest} return a list of pending firensdhip requests
 * 
 * @author Daniele Pancottini
 *
 */

public class InstagramGetPendingFriendshipsRequest extends InstagramGetRequest<InstagramGetPendingFriendshipsResult> {

	@Override
	public String getUrl() {
		return "friendships/pending/";
	}

	@Override
	@SneakyThrows
	public InstagramGetPendingFriendshipsResult parseResult(int resultCode, String content) {
		return parseJson(resultCode, content, InstagramGetPendingFriendshipsResult.class);
	}

	
	
}
