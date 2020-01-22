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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

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
	@SneakyThrows
	public HttpEntity getPayloadEntity() {
		List<NameValuePair> params = new ArrayList<>();
		
		if (maxId != null && !maxId.isEmpty()) {
			params.add(new BasicNameValuePair("reason", "pagination"));
			params.add(new BasicNameValuePair("max_id", maxId));
			params.add(new BasicNameValuePair("is_pull_to_refresh", "0"));
		} else {
			params.add(new BasicNameValuePair("reason", "cold_start_fetch"));
			params.add(new BasicNameValuePair("is_pull_to_refresh", "0"));
			params.add(new BasicNameValuePair("seen_posts", ""));
			params.add(new BasicNameValuePair("unseen_posts", ""));
		}
		params.add(new BasicNameValuePair("is_pull_to_refresh", "0"));
		params.add(new BasicNameValuePair("_csrftoken", api.getOrFetchCsrf()));
		params.add(new BasicNameValuePair("_uuid", api.getUuid()));
		params.add(new BasicNameValuePair("is_prefetch", "0"));
		params.add(new BasicNameValuePair("phone_id", api.getDeviceId()));
		params.add(new BasicNameValuePair("device_id", api.getUuid()));
		params.add(new BasicNameValuePair("battery_level", ThreadLocalRandom.current().nextInt(50, 100) + ""));
		params.add(new BasicNameValuePair("is_charging", "0"));
		params.add(new BasicNameValuePair("will_sound_on", "0"));
		params.add(new BasicNameValuePair("timezone_offset", "0"));
		params.add(new BasicNameValuePair("is_async_ads_in_headload_enabled", "0"));
		params.add(new BasicNameValuePair("rti_delivery_backend", "0"));
		params.add(new BasicNameValuePair("is_async_ads_double_request", "0"));
		params.add(new BasicNameValuePair("is_async_ads_rti", "0"));
		
		return new UrlEncodedFormEntity(params);
	}

	@Override
	public InstagramFeedResult parseResult(int statusCode, String content) {
		return this.parseJson(statusCode, content, InstagramFeedResult.class);
	}

}