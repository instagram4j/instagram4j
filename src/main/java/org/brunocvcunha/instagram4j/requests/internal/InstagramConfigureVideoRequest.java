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
package org.brunocvcunha.instagram4j.requests.internal;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.brunocvcunha.instagram4j.InstagramConstants;
import org.brunocvcunha.instagram4j.requests.InstagramPostRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigureMediaResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;

/**
 * Like Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
@AllArgsConstructor
@Builder
public class InstagramConfigureVideoRequest extends InstagramPostRequest<InstagramConfigureMediaResult> {

	private String uploadId;
	private String caption;
	private long duration;

	@Override
	public String getUrl() {
		return "media/configure/?video=1";
	}

	@Override
	public boolean isSigned() {
		return true;
	}

	@Override
	@SneakyThrows
	public String getPayload() {

		Map<String, Object> likeMap = new LinkedHashMap<>();
		likeMap.put("timezone_offset", TimeZone.getDefault().getRawOffset());
		likeMap.put("_csrftoken", api.getOrFetchCsrf());
		likeMap.put("source_type", "4");
		likeMap.put("_uid", api.getUserId());
		likeMap.put("device_id", api.getDeviceId());
		likeMap.put("_uuid", api.getUuid());
		likeMap.put("upload_id", uploadId);
		Map<String, Object> deviceMap = new LinkedHashMap<>();
		deviceMap.put("manufacturer", InstagramConstants.DEVICE_MANUFACTURER);
		deviceMap.put("model", InstagramConstants.DEVICE_MODEL);
		deviceMap.put("android_version", InstagramConstants.DEVICE_ANDROID_VERSION);
		deviceMap.put("android_release", InstagramConstants.DEVICE_ANDROID_RELEASE);
		likeMap.put("device", deviceMap);
		likeMap.put("length", duration);
		List<Object> clips = Arrays.asList(new Object() {
			@Getter
			private String length = String.valueOf(duration);
			@Getter
			private String source_type = "4";
		});
		likeMap.put("clips", clips);
		likeMap.put("poster_frame_index", 0);
		likeMap.put("audio_muted", false);
		if (caption != null && caption.isEmpty()) {
			likeMap.put("caption", caption);
		}

		ObjectMapper mapper = new ObjectMapper();
		String payloadJson = mapper.writeValueAsString(likeMap);

		return payloadJson;
	}

	@Override
	@SneakyThrows
	public InstagramConfigureMediaResult parseResult(int statusCode, String content) {
		return parseJson(statusCode, content, InstagramConfigureMediaResult.class);
	}

}
