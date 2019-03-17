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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLocationFeedResult;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Location Feed Request
 * @author jpleorx
 */
@RequiredArgsConstructor
@AllArgsConstructor
public class InstagramLocationFeedRequest extends InstagramPostRequest<InstagramLocationFeedResult> {
	@NonNull
	private final String locationId;
	private String maxId;

	@Override
	public String getUrl() {
		return "locations/" + locationId + "/sections/";
	}

	@Override
	@SneakyThrows
	public String getPayload() {
		/*
		 * TODO: Fix tabs somehow
		 * Supposedly tab field should accept either "recent" or "ranked" value, just as your actual location feed tabs on instagram.
		 * When testing both of these options returned the ranked tab's feed.
		 * As of now I have no clue what's wrong and how to fix it. Needs investigating.
		 */
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("rank_token", api.getRankToken());
		map.put("_uuid", api.getUuid());
		map.put("_uid", api.getUserId());
		map.put("_csrftoken", api.getOrFetchCsrf());
		map.put("tab", "recent");
		if (maxId != null && !maxId.isEmpty())
			map.put("max_id", maxId);

		ObjectMapper mapper = new ObjectMapper();
		String payloadJson = mapper.writeValueAsString(map);

		return payloadJson;
	}

	@Override
	public boolean isSigned() {
		return false;
	}

	@Override
	@SneakyThrows
	public InstagramLocationFeedResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramLocationFeedResult.class);
	}
}
