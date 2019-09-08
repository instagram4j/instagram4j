package org.brunocvcunha.instagram4j.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.payload.InstagramFriendshipResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * {@link InstagramApproveFriendshipRequest} send a post request to approve a pending request of a certain user
 * 
 * @author Daniele Pancottini
 *
 */

@AllArgsConstructor
public class InstagramApproveFriendshipRequest extends InstagramPostRequest<InstagramFriendshipResult> {

	@NonNull
	private Long userId;

	@Override
	public String getUrl() {
		return "friendships/approve/" + userId + "/";
	}

	@Override
    @SneakyThrows
    public String getPayload() {
        ObjectMapper mapper = new ObjectMapper();
        
        Map<String, Object> payloadMap = new LinkedHashMap<>();
        payloadMap.put("_uuid", api.getUuid());
        payloadMap.put("_uid", api.getUserId());
        payloadMap.put("user_id", this.userId);
        payloadMap.put("radio_type", "wifi-none");
        payloadMap.put("_csrftoken", api.getOrFetchCsrf());
        
        String payloadJson = mapper.writeValueAsString(payloadMap);

        return payloadJson;
    }
	
	@Override
	public InstagramFriendshipResult parseResult(int resultCode, String content) {
		return parseJson(resultCode, content, InstagramFriendshipResult.class);
	}
	
}
