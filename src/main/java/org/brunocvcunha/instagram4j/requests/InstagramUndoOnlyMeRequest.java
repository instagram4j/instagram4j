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

import java.util.Map;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.brunocvcunha.instagram4j.requests.payload.StatusResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramMediaTypeEnum;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.RequiredArgsConstructor;

/**
 * Request for unarchiving media.
 * 
 * @author Alejandro Perez Moreno
 *
 */
@RequiredArgsConstructor
public class InstagramUndoOnlyMeRequest extends InstagramPostRequest<StatusResult> {
	private final String mediaId;

	@NonNull
	private final InstagramMediaTypeEnum mediaType;

	@Override
	public String getUrl() {
		return "media/" + mediaId + "/undo_only_me/";
	}

    @Override
	@SneakyThrows
	public String getPayload() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("_uuid", api.getUuid());
		map.put("_uid", api.getUserId());
		map.put("_csrftoken", api.getOrFetchCsrf());
		map.put("media_type", mediaType.name());
		map.put("media_id", mediaId);

		return new ObjectMapper().writeValueAsString(map);
	}
	
	@Override
	public StatusResult parseResult(int resultCode, String content) {
		return parseJson(resultCode, content, StatusResult.class);
	}
}
