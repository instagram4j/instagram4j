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
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.client.methods.HttpPost;
import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Timeline Feed Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@RequiredArgsConstructor
@NoArgsConstructor
public class InstagramTimelineFeedRequest extends InstagramPostRequest<InstagramFeedResult> {
    @NonNull
    private String maxId;

    @Override
    public String getUrl() {
	return "feed/timeline/";
    }

    @Override
    public boolean isSigned() {
	return false;
    }
    
    @Override
    public HttpPost getPostRequest() {
	HttpPost post = super.getPostRequest();
	post.addHeader("X-Ads-Opt-Out", "0");
	post.addHeader("X-DEVICE_ID", api.getDeviceId());
	post.addHeader("X-IG-Capabilities", InstagramConstants.CAPABILITIES);
	
	return post;
    }

    @Override
    @SneakyThrows
    public String getPayload() {
	ObjectMapper mapper = new ObjectMapper();
	Map<String, Object> payload = new LinkedHashMap<>();
	if (maxId != null && !maxId.isEmpty()) {
	    payload.put("reason", "pagination");
	    payload.put("max_id", maxId);
	    payload.put("is_pull_to_refresh", 0);
	} else {
	    payload.put("reason", "cold_start_fetch");
	    payload.put("is_pull_to_refresh", 0);
	    payload.put("seen_posts", "");
	    payload.put("unseen_posts", "");
	}
	payload.put("is_pull_to_refresh", 0);
	payload.put("_csrftoken", api.getOrFetchCsrf());
	payload.put("_uuid", api.getUuid());
	payload.put("is_prefetch", 0);
	payload.put("phone_id", api.getDeviceId());
	payload.put("device_id", api.getUuid());
	payload.put("battery_level", ThreadLocalRandom.current().nextInt(50, 100));
	payload.put("is_charging", 0);
	payload.put("will_sound_on", 0);
	payload.put("timezone_offset", 0);
	payload.put("is_async_ads_in_headload_enabled", 0);
	payload.put("rti_delivery_backend", 0);
	payload.put("is_async_ads_double_request", 0);
	payload.put("is_async_ads_rti", 0);
	return mapper.writeValueAsString(payload);
    }

    @Override
    public InstagramFeedResult parseResult(int statusCode, String content) {
	return this.parseJson(statusCode, content, InstagramFeedResult.class);
    }

}
