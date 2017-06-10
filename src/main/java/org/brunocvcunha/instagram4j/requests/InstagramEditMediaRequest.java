/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brunocvcunha.instagram4j.requests;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.brunocvcunha.instagram4j.requests.payload.StatusResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

/**
 * Request for editing media.
 * 
 * @author Evgeny Bondarenko (evgbondarenko@gmail.com)
 *
 */
@RequiredArgsConstructor
public class InstagramEditMediaRequest extends InstagramPostRequest<StatusResult> {
	private final String mediaId;
	@NonNull
	private final String captionText;
	private List<UserTag> userTag;
	private UserTagAction action;

	@Override
	public String getUrl() {
		return "media/" + mediaId + "/edit_media/";
	}

	@Override
	@SneakyThrows
	public String getPayload() {

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("_uuid", api.getUuid());
		map.put("_uid", api.getUserId());
		map.put("_csrftoken", api.getOrFetchCsrf());
		map.put("caption_text", captionText);

		if (userTag != null && userTag.size() > 0) {
			StringBuilder sb = new StringBuilder();

			if (action == UserTagAction.ADD) {
				sb.append("{\"removed\":[],\"in\":[");
				for (UserTag ut : userTag) {
					sb.append("{\"position\":[").append(ut.getPositionX()).append(",").append(ut.getPositionY()).append("],\"user_id\":\"")
							.append(ut.getUserId()).append("\"},");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("]}");
			} else {
				sb.append("{\"removed\":[");
				for (UserTag ut : userTag) {
					sb.append("\"").append(ut.getUserId()).append("\",");
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("],\"in\":[]}");
			}

			map.put("usertags", sb.toString());
		}

		ObjectMapper mapper = new ObjectMapper();
		String payloadJson = mapper.writeValueAsString(map);
		return payloadJson;
	}

	@Override
	public StatusResult parseResult(int resultCode, String content) {
		return parseJson(resultCode, content, StatusResult.class);
	}

	public void setUserTag(List<UserTag> userTag, UserTagAction action) {
		this.userTag = userTag;
		this.action = action;
	}

	public enum UserTagAction {
		ADD, REMOVE
	}

	@Getter
	@Setter
	public class UserTag {
		private String userId;
		private float positionX;
		private float positionY;

		public UserTag(String userId, float positionX, float positionY) {
			this.userId = userId;
			this.positionX = positionX;
			this.positionY = positionY;
		}
	}
}
